package com.example.administrator.kotlintest.util;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.f2prateek.rx.preferences2.RxSharedPreferences;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;

/**
 * Created by hexun on 2017/10/17.
 * @author ZHANG PEIYUAN
 */

public class SpManager {

    private static RxSharedPreferences rxPreferences;
    private static SharedPreferences defaultSp;
    private static SharedPreferences objectSp;
    private static SpManager spManager;
    private Application application;
    private static final String NEWS_TITLE_FILE = "newsTitles";
    private static String preferencesFileName = "read_id";
    private SpManager(Application context) {
        application = context;
        defaultSp = PreferenceManager.getDefaultSharedPreferences(context);
        rxPreferences = RxSharedPreferences.create(defaultSp);
    }

    public static synchronized void init(Application context) {
        if (spManager == null){
            spManager = new SpManager(context);
            spManager.prefetch();
        }
    }


    public static SpManager getPreferences(){
        if (spManager == null){
            throw new RuntimeException("init First");
        }
        return spManager;
    }


    /**
     * 获取对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObjetct(String key, Class<T> clazz) {
        if(!TextUtils.isEmpty(key)) {
            T t = null;
            if (objectSp == null){
                objectSp = application.getSharedPreferences("object_sharePreference", Context.MODE_PRIVATE);
            }
            if(objectSp.contains(key)) {
                String objStr = objectSp.getString(key, (String)null);
                if(!TextUtils.isEmpty(objStr)) {
                    byte[] objArray = Base64.decode(objStr, 2);
                    ByteArrayInputStream bais = new ByteArrayInputStream(objArray);
                    ObjectInputStream ois = null;

                    try {
                        ois = new ObjectInputStream(bais);
                        t = (T)ois.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(bais != null) {
                                bais.close();
                            }
                            if(ois != null) {
                                ois.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
            return t;
        } else {
            throw new IllegalArgumentException("key or context is null。");
        }
    }

    /**
     * 存储对象
     * @param key
     * @param obj
     */
    public void saveObject(String key, Object obj) {
        if(!TextUtils.isEmpty(key) && obj != null) {
            if (objectSp == null){
                objectSp = application.getSharedPreferences("object_sharePreference", Context.MODE_PRIVATE);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;

            try {
                oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String e = new String(Base64.encodeToString(baos.toByteArray(), 2));
                objectSp.edit().putString(key, e).commit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(baos != null) {
                        baos.close();
                    }
                    if(oos != null) {
                        oos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new IllegalArgumentException("parameters is null。");
        }
    }


    /**
     * 删除对象
     * @param key
     */
    public void removeObject(String key) {
        if(!TextUtils.isEmpty(key)) {
            if (objectSp == null){
                objectSp = application.getSharedPreferences("object_sharePreference", Context.MODE_PRIVATE);
            }
            objectSp.edit().remove(key).apply();
        } else {
            throw new IllegalArgumentException("parameters is null。");
        }
    }



    public Observable<String> getStringObservable(String key, String defaultValue){
        return rxPreferences.getString(key,defaultValue)
                .asObservable();
    }
    public Observable<Integer> getIntObservable(String key, int defaultValue){
        return rxPreferences.getInteger(key,defaultValue)
                .asObservable();
    }
    public Observable<Long> getLongObservable(String key, Long defaultValue){
        return rxPreferences.getLong(key,defaultValue)
                .asObservable();
    }
    public Observable<Float> getFloatObservable(String key, float defaultValue){
        return rxPreferences.getFloat(key,defaultValue)
                .asObservable();
    }
    public Observable<Boolean> getBooleanObservable(String key, boolean defaultValue){
        return rxPreferences.getBoolean(key,defaultValue)
                .asObservable();
    }




    /**
     * SP中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, @NonNull final String value) {
        put(key, value, false);
    }

    /**
     * SP中写入String
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, @NonNull final String value, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putString(key, value).commit();
        } else {
            defaultSp.edit().putString(key, value).apply();
        }
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull final String key, @NonNull final String defaultValue) {
        return defaultSp.getString(key, defaultValue);
    }

    /**
     * SP中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final int value) {
        put(key, value, false);
    }

    /**
     * SP中写入int
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final int value, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putInt(key, value).commit();
        } else {
            defaultSp.edit().putInt(key, value).apply();
        }
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull final String key, final int defaultValue) {
        return defaultSp.getInt(key, defaultValue);
    }

    /**
     * SP中写入long
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final long value) {
        put(key, value, false);
    }

    /**
     * SP中写入long
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putLong(key, value).commit();
        } else {
            defaultSp.edit().putLong(key, value).apply();
        }
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return defaultSp.getLong(key, defaultValue);
    }

    /**
     * SP中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final float value) {
        put(key, value, false);
    }

    /**
     * SP中写入float
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final float value, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putFloat(key, value).commit();
        } else {
            defaultSp.edit().putFloat(key, value).apply();
        }
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return defaultSp.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final boolean value) {
        put(key, value, false);
    }

    /**
     * SP中写入boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final boolean value, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putBoolean(key, value).commit();
        } else {
            defaultSp.edit().putBoolean(key, value).apply();
        }
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return defaultSp.getBoolean(key, defaultValue);
    }

    /**
     * SP中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void put(@NonNull final String key, @NonNull final Set<String> values) {
        put(key, values, false);
    }

    /**
     * SP中写入String集合
     *
     * @param key      键
     * @param values   值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, @NonNull final Set<String> values, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().putStringSet(key, values).commit();
        } else {
            defaultSp.edit().putStringSet(key, values).apply();
        }
    }

    /**
     * SP中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull final String key, @NonNull final Set<String> defaultValue) {
        return defaultSp.getStringSet(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return defaultSp.getAll();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull final String key) {
        return defaultSp.contains(key);
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(@NonNull final String key) {
        remove(key, false);
    }

    /**
     * SP中移除该key
     *
     * @param key      键
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().remove(key).commit();
        } else {
            defaultSp.edit().remove(key).apply();
        }
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        clear(false);
    }

    /**
     * SP中清除所有数据
     *
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            defaultSp.edit().clear().commit();
        } else {
            defaultSp.edit().clear().apply();
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * SP中写入并保存保存新闻栏目ids
     * @param context
     * @param ids
     */
    public static void writeTitlePreference(Context context, String ids) {
        SharedPreferences newsTitle = context.getSharedPreferences(NEWS_TITLE_FILE, 0);
        SharedPreferences.Editor editor = newsTitle.edit();
        editor.putString("ids", ids);
        editor.apply();
    }
    /**
     * conSP中读出保存的新闻栏目ids
     * @param context
     */
    public static String readTitlePreference(Context context) {
        SharedPreferences newsTitle = context.getSharedPreferences(NEWS_TITLE_FILE, 0);
        String ids = newsTitle.getString("ids", null);
        return ids;
    }

    private static Map<SpKey, Object> valuesCache = Collections.synchronizedMap(new HashMap<SpKey, Object>());
    private static ArrayList<SpKey> prefetchKeys = new ArrayList();
    private boolean isPrefetch = false;

    public static void addPrefetch(SpKey key) {
        prefetchKeys.add(key);
    }

    private SharedPreferences getSharedPreferences(String name) {
        return application.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public <T> T getValue(SpKey<T> key, T defaultValue) {
        if (key == null) {
            return defaultValue;
        }
        Object value = valuesCache.get(key);
        Class<T> valueClass = key.getValueClass();
        if (valueClass.isInstance(value)) {
            return valueClass.cast(value);
        }
        return defaultValue;
    }

    public <T> void saveValue(SpKey<T> key, T value) {
        if (key == null) {
            return;
        }
        SharedPreferences.Editor editor = application.getSharedPreferences(key.getKey(), Context.MODE_PRIVATE).edit();
        final Class<T> valueClass = key.getValueClass();
        if (value == null) {
            valuesCache.remove(key);
            editor.remove(key.getKey()).apply();
        } else {
            valuesCache.put(key, value);
            try {
                if (String.class == valueClass){
                    editor.putString(key.getKey(), String.class.cast(value)).apply();
                } else if (Integer.class == valueClass) {
                    editor.putInt(key.getKey(), Integer.class.cast(value)).apply();
                } else if (Long.class == valueClass) {
                    editor.putLong(key.getKey(), Long.class.cast(value)).apply();
                } else if (Float.class == valueClass) {
                    editor.putFloat(key.getKey(), Float.class.cast(value)).apply();
                } else if (Boolean.class == valueClass) {
                    editor.putBoolean(key.getKey(), Boolean.class.cast(value)).apply();
                }
            } catch (ClassCastException e) {

            }
        }
    }

    private SharedPreferences getSP(Context context, String spName) {
        if (spName != null) {
            return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
        return defaultSp;
    }

    private void prefetch() {
        if (prefetchKeys.isEmpty()) {
            return;
        }
        isPrefetch = true;
        new AsyncTask<Void, Void, Map<SpKey, Object>>(){
            @Override
            protected Map<SpKey, Object> doInBackground(Void... voids) {
                final ArrayList<SpKey> keys = new ArrayList<>(prefetchKeys);
                final Map<SpKey, Object> valuesMap = new HashMap<>();
                final Context context = application;
                SharedPreferences sp;
                Object value;
                Class valueClass;
                for (SpKey key : keys) {
                    value = null;
                    valueClass = key.getValueClass();
                    sp = getSP(context, key.getSpName());
                    if (String.class == valueClass){
                        value = sp.getString(key.getKey(), null);
                    } else if (Integer.class == valueClass) {
                        int i = sp.getInt(key.getKey(), Integer.MAX_VALUE);
                        if (i != Integer.MAX_VALUE) {
                            value = Integer.valueOf(i);
                        }
                    } else if (Long.class == valueClass) {
                        long l = sp.getLong(key.getKey(), Long.MAX_VALUE);
                        if (l != Long.MAX_VALUE) {
                            value = Long.valueOf(l);
                        }
                    } else if (Float.class == valueClass) {
                        float f = sp.getFloat(key.getKey(), Float.MAX_VALUE);
                        if (f != Float.MAX_VALUE) {
                            value = Float.valueOf(f);
                        }
                    } else if (Boolean.class == valueClass) {
                        if (sp.contains(key.getKey())) {
                            value = sp.getBoolean(key.getKey(), false);
                        }
                    }
                    if (value != null) {
                        valuesMap.put(key, value);
                    }
                }
                return valuesMap;
            }

            @Override
            protected void onPostExecute(Map<SpKey, Object> result) {
                if (!result.isEmpty()) {
                    valuesCache.putAll(result);
                }
                isPrefetch = false;
            }
        }.executeOnExecutor(smallTaskService);
    }
    private ExecutorService smallTaskService = Executors.newCachedThreadPool();
    /*******************已阅新闻置灰************************/

    /**
     * 获取已读要闻
     * @return 已读要闻id字符串
     */
    public String getNewsRead(){
        SharedPreferences sp = getSharedPreferences(preferencesFileName);
        return sp.getString("ids", "");
    }

    /**
     * 添加已读要闻
     * @param id  要闻id
     * @param isCommit 是否commit : true {@link SharedPreferences.Editor#commit()} false {@link SharedPreferences.Editor#apply()}
     */
    public void putNewsRead(String id,boolean isCommit){
        SharedPreferences sp = getSharedPreferences(preferencesFileName);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("ids",id);

        if(isCommit) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

}
