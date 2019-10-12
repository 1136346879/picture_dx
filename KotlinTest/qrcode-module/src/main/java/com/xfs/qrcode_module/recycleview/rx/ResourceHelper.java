package com.xfs.qrcode_module.recycleview.rx;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;


/**
 * @author zhangpeiyuan
 *         <p>
 *         yangyi updated at 2017年11月8日11:07:46
 *         <p>
 *         update reason: 因为是helper类，所以是可以获取实例的。
 *         util是直接提供静态method。  详见util类和helper类的区别。
 *         故修改成非静态方法，通过单例获取实例对象。
 */

public class ResourceHelper {

    private static ResourceHelper resourceHelper;

    private static Application app;
//    private static BoxStore favoriteBox;
//    private static BoxStore viewedBox;
//    private static Box<NewsInfo> favorite;
//    private static Box<NewsInfo> viewed;

    public synchronized static ResourceHelper getInstance() {
        if (resourceHelper == null) {
            return new ResourceHelper();
        }
        return resourceHelper;
    }

    public ResourceHelper() {
    }

    public void init(Application context) {
        if (app == null) {
            app = context;
//            favoriteBox = MyObjectBox.builder().name("favorite").androidContext(context).build();
//            viewedBox = MyObjectBox.builder().name("viewed").androidContext(context).build();
//            favorite = favoriteBox.boxFor(NewsInfo.class);
//            viewed = viewedBox.boxFor(NewsInfo.class);
        }
    }


    public String getString(@StringRes int id) {
        return app.getResources().getString(id);
    }

    public String[] getStringArray(@ArrayRes int id)
            throws Resources.NotFoundException {
        return app.getResources().getStringArray(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Drawable getDrawable(@DrawableRes int id) {
        return app.getResources().getDrawable(id, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public int getColor(@ColorRes int id) {
        return app.getResources().getColor(id, null);
    }


    public AssetManager getAssets() {
        return app.getResources().getAssets();
    }


    /**
     * 添加到我的收藏
     */
//    public void addToFavorite(final String subType, final String title,
//                              final String time, final long id, final String channel,
//                              final String media, Observer<Boolean> observer) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                NewsInfo newsInfo = favorite.query()
//                        .equal(NewsInfo_.__ID_PROPERTY, id)
//                        .build().findFirst();
//                if (newsInfo != null) {
//                    newsInfo.setTime(time);
//                    newsInfo.setTitle(title);
//                    newsInfo.setSubtype(subType);
//                    newsInfo.setChannel(channel);
//                    newsInfo.setMedia(media);
//                } else {
//                    newsInfo = new NewsInfo();
//                    newsInfo.setTime(time);
//                    newsInfo.setTitle(title);
//                    newsInfo.setSubtype(subType);
//                    newsInfo.setChannel(channel);
//                    newsInfo.setMedia(media);
//                    newsInfo.setNewsId(id);
//                }
//                newsInfo.setAddTime(System.currentTimeMillis());
//                favorite.put(newsInfo);
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    /**
//     * 添加到我的收藏(列表)
//     */
//    public void addToFavorite(final List<NewsInfo> infos, Observer<Boolean> observer) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                favorite.put(infos);
//            }
//        }).subscribeOn(Schedulers.single())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    /**
//     * 移除我的收藏
//     */
//    public void removeFromFavorite(final long id, Observer<Boolean> observer) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                favorite.remove(id);
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//
//    public void isFavorite(final long id, Observer<Boolean> observer) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                NewsInfo exist = favorite.query()
//                        .equal(NewsInfo_.__ID_PROPERTY, id)
//                        .build().findFirst();
//                if (exist != null) {
//                    e.onNext(true);
//                } else {
//                    e.onNext(false);
//                }
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    /**
//     * 移除所有收藏
//     */
//    public void clearMyFavorite(Observer<Boolean> observer) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                favorite.removeAll();
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//
//    /**
//     * 添加到我的浏览历史
//     */
//    public void addToViewHistory(final String subType, final String title,
//                                 final String time, final long id, final String channel,
//                                 final String media) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                NewsInfo newsInfo = viewed.query()
//                        .equal(NewsInfo_.__ID_PROPERTY, id)
//                        .build().findFirst();
//                if (newsInfo != null) {
//                    newsInfo.setTime(time);
//                    newsInfo.setTitle(title);
//                    newsInfo.setSubtype(subType);
//                    newsInfo.setChannel(channel);
//                    newsInfo.setMedia(media);
//                } else {
//                    newsInfo = new NewsInfo();
//                    newsInfo.setTime(time);
//                    newsInfo.setTitle(title);
//                    newsInfo.setSubtype(subType);
//                    newsInfo.setChannel(channel);
//                    newsInfo.setMedia(media);
//                    newsInfo.setNewsId(id);
//                }
//                newsInfo.setAddTime(System.currentTimeMillis());
//                viewed.put(newsInfo);
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .subscribe();
//    }
//
//    /**
//     * 移除我的浏览历史
//     */
//    public void removeFromViewHistory(final long id) {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                viewed.remove(id);
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .subscribe();
//    }
//
//
//    /**
//     * 移除所有浏览历史
//     */
//    public void clearMyViewHistory() {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                viewed.removeAll();
//                e.onNext(true);
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.single())
//                .subscribe();
//    }
//
//    /**
//     * 迁移数据
//     */
//    public void migrateData() {
//        Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                boolean isMigrated = SpManager.getPreferences().contains(TrainingConst.SharedPreferences.DATA_MIGRATE);
//                if (!isMigrated) {
//                    LocalDbHelper helper = LocalDbHelper.getInstance();
//                    helper.init(app);
//                    if (helper.dBFileIsExist()) {
//                        List<NewsInfo> infoList = helper.queryAllFromSelectionTable();
//                        if (infoList != null && !infoList.isEmpty()) {
//                            favorite.put(infoList);
//                        }
//                    }
//                    SpManager.getPreferences().put(TrainingConst.SharedPreferences.DATA_MIGRATE, true);
//                    helper.deleteOldDBFile();
//                }
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.io())
//                .subscribe();
//    }
//
//
//    public Box<NewsInfo> getFavoriteBox() {
//        return favorite;
//    }
//
//    public Box<NewsInfo> getViewedBox() {
//        return viewed;
//    }


}
