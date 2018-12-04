package com.example.administrator.kotlintest.ui.activity

import android.util.Log
import com.example.administrator.kotlintest.Doctor
import com.example.administrator.kotlintest.Loglevel
import com.example.administrator.kotlintest.MaNong
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.inner.Outter
import com.example.administrator.kotlintest.inner.Outter.Inner1
import com.example.administrator.kotlintest.inner.View
import com.example.administrator.kotlintest.inner.onClickListener
import com.example.administrator.kotlintest.ui.entity.学生
import com.example.administrator.kotlintest.ui.qrcode.CaptureActivity
import com.example.administrator.kotlintest.widget.ToastUtilKt
import com.example.baselibrary.ui.activity.BaseActivity
import com.example.baselibrary.widgets.EasyPickerView
import com.xfs.fsyuncai.ui.main.home.banner.GlideImageLoader
import com.xfs.qrcode_module.manager.ScanManager
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.activity_maintwo.*
import org.jetbrains.anko.intentFor

/**
 * 第三方imagebanner 轮播图
 *
 * 扫一扫入口
 */
class ThirdPartBannerZxingAcitivity: BaseActivity(){

    private  val aBoolean : Boolean = false
    private  var typeInt : Int = 0
    private  var typeDouble : Double = 0.0
    private  var typeFloat : Float = 1.0F
    private  var typeLong : Long = 1L
    private  var typeString : String? = null
    private var typeShort: Short = 2
    private var typeChar: Char = 'a'

    val FINAL_NI_HAO : String = "nihao"
    var list_path = arrayListOf(
            "http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg"
            ,"http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg"
    ,"http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg"
    ,"http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg"
    ,"http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg"
    ,"http://h.hiphotos.baidu.com/image/pic/item/b999a9014c086e064a76b12f0f087bf40bd1cbfc.jpg")
    var list_title = arrayListOf("爱党","爱祖国","爱人民","爱妹子","爱学习","爱人生")


        var arrayList= arrayListOf(

                "单开",
                "分摊"
        )
                override fun initLayout(): Int {

//                    sum("你好吗？")
//           ToastUtilKt.showCustomToast(         sumAandB(1,3).toString())
//                ToastUtilKt.showToast(    sumBandA.invoke(5,6).toString())
        return R.layout.activity_maintwo
    }

    fun sum(abc:String) {
        ToastUtilKt.showCustomToast("$abc")
    }

   val sumAandB =  fun(args1:Int, args2 :Int): Int{
        return args1+args2

    }
    val sumBandA = {args1:Int,args2:Int -> args1+args2}
    override fun initView() {
//        FINAL_NI_HAO = "564"
        //
        Log.e("initView","initView")


        val string = "你好吗？"
//       ToastUtilKt.showToast (string.times(5))
//       ToastUtilKt.showToast (string*15)

       val inner1 =  Inner1()
        val inner2 = Outter().Inner2()

        val view = View()
        view.onClickListener = object:onClickListener {
            override fun onClicks() {
            }
        }



Loglevel.values().map {

//ToastUtilKt.showCustomToast(it.toString())

}

        /**
         * 第三方的轮播图
         */
        homeBanner//设置banner样式
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                //设置图片加载器
                .setImageLoader(GlideImageLoader())
                //设置图片集合
                .setImages(list_path)
                //设置banner动画效果
                .setBannerAnimation(Transformer.Default)
                //设置标题集合（当banner样式有显示title时）
                .setBannerTitles(list_title)
                //设置轮播时间
                .setDelayTime(500)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.CENTER)
                //banner设置方法全部调用完毕时最后调用
                .start()

        homeBanner.setOnBannerListener {
        val bean = list_path[it]




        }

    }

    override fun initData() {
        //扫一扫（仿照微信界面）
        saoyisao.setOnClickListener {  startActivity(this!!.intentFor<CaptureActivity>()) }
        //扫一扫 （自定义）
        looklook.setOnClickListener {

            ScanManager.getInstance().openScan(this)
//            startActivity(this.intentFor<com.xfs.qrcode_module.activity.CaptureActivity>())
        }
        //进入自定义的轮播图
        jump_javaclass.setOnClickListener { startActivity(this!!.intentFor<CustomerBannerActivityJava>()) }
        var a = 1
        val s1 = "a is $a"
        a = 2

        val s2 = "${s1.replace("is","was")},but now is $a"
        //
        Log.e("s2",s2)
        mainactivity2.setOnClickListener {

//            startActivity(this!!.intentFor<MainActivity>())
//            ScanManager.getInstance().openScan(this@MainAcitivitytwo)

//            maxInt(a,20)


        }

        ep_view.setDataList(arrayList)
        ep_view.setOnScrollChangedListener(object :EasyPickerView.OnScrollChangedListener{
            override fun onScrollChanged(curIndex: Int) {
                //滑动时选中项发生变化
//                ToastUtil.showToast(this@MainAcitivitytwo,"滑动时选中项发生变化$curIndex")
            }

            override fun onScrollFinished(curIndex: Int) {
                //滑动结束
//               ToastUtil.showToast(this@MainAcitivitytwo,"滑动结束$curIndex",Toast.LENGTH_LONG)
            }

        })

//区间
        val rangela : IntRange = 0..1024  //[0,1024]
        val rangeLongb : IntRange = 0 until 1024 //[0,1024)
        val emptyRange : IntRange = 0..-1
//         ToastUtilKt.showCustomToast(rangela.contains(50).toString())
//        ToastUtilKt.showToast((50 in rangela ).toString())

//        for (i in rangela)   ToastUtilKt.showToast("$i,")

//数组
//        val arrayOfInt : IntArray = intArrayOf(1,3,5,7)
        val arrayCharArray : CharArray = charArrayOf('c','b','a')
        val arrayStringArray : Array<String> = arrayOf("我","是","谁")
        val arrayOf徐盛 : Array<学生> = arrayOf(学生("li"),学生("wang"))

//        ToastUtilKt.showCustomToast(arrayOfInt.size.toString())
//        for (i in arrayCharArray) ToastUtilKt.showToast(i.toString())

//        ToastUtilKt.showToast(arrayOf徐盛[1].toString())
        arrayOf徐盛[0] = 学生("火")
//        ToastUtilKt.showCustomToast(arrayOf徐盛[0].toString())

//        ToastUtilKt.showCustomToast(arrayCharArray.joinToString("")) //cba
//        ToastUtilKt.showCustomToast(arrayCharArray.joinToString())//c，b，a
//        ToastUtilKt.showCustomToast(arrayOfInt.slice(0..2).toString())

//        arrayOfInt.forEach {
//            ToastUtilKt.showToast(it.toString())
//        }

        arrayCharArray.forEach forEach@ {
            if(it == 'b')return@forEach
//            ToastUtilKt.showToast(it.toString())
        }

//        ToastUtilKt.showCustomToast("end")

        for (i in arrayCharArray){
//            ToastUtilKt.showToast(i.toString())
        }

//        学生("元")on ("救命")


//        val studentBean = 学生("丁")
//        studentBean.play("拉丁舞")

//        method(3.0,*arrayOfInt, adad ="nihao")

//        val computer = Computer()
//        val usbMouse = UsbMouse("清华同方")
//        val luojIUsbMouse = LuojIUsbMouse()
//        computer.addInputDevice(luojIUsbMouse)
        val doctor = Doctor()
        doctor.work()
        val maNong = MaNong()
//        maNong.work()


    }

    fun maxInt(a:Int,b:Int): Int {
//        return if(a>b){
//            ToastUtilKt.showCustomToast("a是最大值： $a")
//            a
//        }else{
//            ToastUtilKt.showCustomToast("b是最大值： $b")
//            b
//        }
        val x = 5
//        when(x){
//            5 -> ToastUtilKt.showCustomToast("你好，x 是 5")
//            is Int -> ToastUtilKt.showCustomToast("true")
//            in 1..100 -> ToastUtilKt.showCustomToast("yes")
//            else -> ToastUtilKt.showCustomToast("else")
//
//        }
//        val fruits = listOf("banana", "avocado", "apple", "kiwi")
//        fruits
//                .filter { it.startsWith("a") }
//                .sortedBy { it }
//                .map { it.toUpperCase() }
//                .forEach {  ToastUtilKt.showCustomToast(it)}



        val arrayINts  = arrayOf(1,3,4,5)



        do {
            val y = 5
        } while (y != null) // y 在这是可见的

        var ui = return  if(a>b){
//            ToastUtilKt.showCustomToast("a是最大值： $a")
            a
        }else{
//            ToastUtilKt.showCustomToast("b是最大值： $b")
            b
        }





    }
    fun method(dou: Double = 0.3, vararg asd: Int, adad:String){
        ToastUtilKt.showCustomToast(dou.toString())
        asd.forEach {
            ToastUtilKt.showToast(it.toString())
        }

    }




    fun isOrEmpty(vararg args :String) : Boolean{

        return args.isEmpty()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        ScanManager.getInstance().onActivityResult(this,
//                requestCode,
//                resultCode,
//                data)
//    }



}