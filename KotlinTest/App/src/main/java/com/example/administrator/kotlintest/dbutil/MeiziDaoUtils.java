package com.example.administrator.kotlintest.dbutil;

import android.content.Context;
import android.util.Log;

import com.example.administrator.dbdao.dbdao.MeiziDao;
import com.example.administrator.kotlintest.entity.daoentity.Meizi;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MeiziDaoUtils {

    private static final String TAG = MeiziDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public MeiziDaoUtils(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param meizi
     * @return
     */
    public boolean insertMeizi(Meizi meizi){
        boolean flag = false;
        flag = mManager.getDaoSession().getMeiziDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + meizi.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<Meizi> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Meizi meizi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(meizi);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     * @param meizi
     * @return
     */
    public boolean updateMeizi(Meizi meizi){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public boolean deleteMeizi(Meizi meizi){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Meizi.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Meizi> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(Meizi.class);
    }

    /**
     * 各种条件查询：
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Meizi queryMeiziById(long key){
        return mManager.getDaoSession().load(Meizi.class, key);
    }

    /**
     * 各种条件查询：
     * 使用native sql进行查询操作
     *   QueryBuilder能够让你在不涉及SQL语句的情况下查询实体。写SQL有几个缺点，首先是易错的，
     * 其次是要在运行时才知道有没有问题（假如属性名是pid，你写成了id，也要到运营时才会崩溃），
     * QueryBuilder能够在编译时检查错误（如属性的引用是否错误）。
         关于Api：在org.greenrobot.greendao.query包下，QueryBuilder类中查看其方法；构造函数可以传
     递我们的Xxx实体类型，查询方法有很多逻辑的where方法。where方法中需要设置WhereCondition类型的条件参数，
     而在org.greenrobot.greendao包下的Property类中，每一种操作符的方法都返回WhereCondition类型。获取Property实例
     则不需要我们去做，在我们的XxxDao中已经有对应的提供，例如我们这里的MeiziDao.Properties.XXX。
            LazyList懒加载是指一次性查完数据保存在内存中，然后关闭所有连接，再次查询时从内存中获取。一般查询大数据量时用。
     */
    public List<Meizi> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Meizi.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Meizi> queryMeiziByQueryBuilder(long id){
        QueryBuilder<Meizi> queryBuilder = mManager.getDaoSession().queryBuilder(Meizi.class);
        return queryBuilder.where(MeiziDao.Properties._id.eq(id)).list();
    }

   // 查询所有记录： queryMeiziById(1008l)
    //根据主键查询记录： queryAllMeizi();
}
