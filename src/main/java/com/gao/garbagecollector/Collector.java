package com.gao.garbagecollector;

/**
 * GC算法 :
 * 	1.引用计数(不采用,互相引用)
 * 	2.复制	(新生代)
 * 	3.标记清除 (没有复制 快 缺点:没有连续的内存空间)  (老年代多一些)
 * 	4.标记整理 (先标记再压缩 有连续的内存空间 缺点: 需要移动对象的时间成本) (老年代多一些)
 *
 * 垃圾回收器
 *
 * 新生代收集器：
 * Serial    *串行*   1:1
 * ParNew  N:1
 * Parallel Scavenge  *并行* N:N
 * 老年代收集器：
 * Serial Old
 * CMS  				*并发*
 * Parallel Old
 * 堆内存垃圾收集器：*G1*
 *
 *
 * 1.Serial:  发生GC的时候由单线程进行垃圾回收,期间应用程序会暂停,由于效率问题很少使用这个方法了 -XX:+UseSerialGC :表示新生代老年代都使用串行回收器,新: 复制 老:标记整理
 * 2.Parallel: 发生GC的时候由多线程进行垃圾回收,期间应用程序会暂停,比较适用于弱交互 -XX:+UseParallelGC -XX:ParallelGCThreads=数字
 * 3.CMS: 不会暂停应用程序,(不一定并行,可能交替进行)
 * 4.ParNew:   -Xmx5m -Xmx5m -XX:+PrintGCDetails -XX:+UseParNewGC -XX:+PrintCommandLineFlags
 * Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release 使用ParNew的时候年轻代使用Parallel 老年代使用serial
 * 5.Parallel Old -XX:+UseParallelOldGC  2,6可以互相激活
 * 6.CMS:老年代 并发标记清除 -XX:+UseConcMarkSweepGC 如果出错Serial Old 将作为CMS 的备用收集器 ParNew(年轻代使用)
 * 	分为四步:1.初始标记  标记GCRoot能直接关联的对象,速度很快,仍然要暂停所有线程
 * 			 2.并发标记	 进行GCRoot跟踪过程,和用户线程一起工作不需要暂停工作线程,最主要标记过程,标记全部对象
 * 			 3.重新标记  正式清理前做个修正,仍会暂停
 * 			 4.并发清除	 清除GCRoot不可达对象和用户线程一起工作不需要暂停工作线程基于标记结果直接清除对象
 * 			总结:虽然仍会有STW 操作但是由于耗时较多的并发标记还有并发清除,可以和线程一起,因此在总体上看CMS收集器内存回收和用户线程是一起并发执行的
 * 	优点:并发清除低停顿 缺点cpu压力大,采用标记清除会产生大量的内存碎片 -XX:CMSFullGCBeForeCompaction=  执行多少次CMS之后进行标记压缩的FullGC
 * 	[CMS-concurrent-mark-start]
 * 	[CMS-concurrent-mark: 0.001/0.001 secs]
 * 	[CMS-concurrent-preclean-start]
 * 	[CMS-concurrent-preclean: 0.000/0.000 secs]
 * 	[GC (CMS Final Remark) ]
 * 	[CMS-concurrent-sweep-start]
 * 	[CMS-concurrent-sweep: 0.000/0.000 secs]
 * 	[CMS-concurrent-reset-start]
 * 	[CMS-concurrent-reset: 0.000/0.000 secs]
 * 7.G1: -XX:+UseG1GC 年轻代,老年代均使用G1
 * 	具有如下特性:1.能与应用线程并发执行 2.整理空闲空间更快 3.需要更多时间来预测GC停顿时间 4.不希望牺牲大量的吞吐性能 5.不需要更多的java Heap
 * 	G1对比CMS的优点:1.不会产生过多的内存碎片2.STW时间可控,用户可以指定期望停顿时间
 * 	1.G1 充分利用多CPU,多核硬件环境尽量缩短STW
 * 	2.整体利用标记整理,局部使用复制不会产生内存碎片
 * 	3.******宏观上看不分年轻代年老代,而是把内存划分为多个子区域****** 细分还是分为Eden survivor old
 * 	4.本身依然存在年轻代年老代,也就是说还是会使用不同的方式来分别处理不同区域
 * 	5.G1 虽然是分带收集器但是也只是逻辑上的分代,或者说每个分区都可能随G1的运行在不同代之间前后转换
 * G1 底层原理最大的好处是化整为零,避免全内存扫描,只需要按照区域来进行扫描即可  OutOfMemeryDemo2中测试
 * 	-XX:G1HeapRegionSize=n (1-32m) 默认分2048分区 32m*2048=64G内存
 * 	收集算法:
 * 	年轻代
 * 	1.eden 移动到survivor中 如果survivor中内存不足 Eden晋升到old区
 * 	2.Survivor区数据以用到新的Survivor中,部分晋升到old区
 * 	3.eden收集干净  结束
 *
 *
 * 如何查看默认的垃圾回收器
 * java -XX:+PrintCommandLineFlags -version
 * -XX:+UseParallelGC
 *
 *
 * GC约定参数说明:
 * 	DefNew: default New Generation
 * 	Tenurde: Old
 * 	PerNew: Parallel New Generation
 * 	PsYoungGen: Parallel Scavenge
 * 	ParOldGen: Parallel Old Generation
 *
 * 如何选择合适的垃圾收集器:
 * 1.单CPU,或小内存,单机程序 -XX:+UseSerialGC
 * 2.多CPU大吞吐量,或后台计算型 -XX:+UseParallelGC 或 -XX:+UseParallelOldGC
 * 3.多CPU,低请求停顿时间 需要快速响应  -XX:+UseConcMarkSweepGC
 */
public class Collector {

}
