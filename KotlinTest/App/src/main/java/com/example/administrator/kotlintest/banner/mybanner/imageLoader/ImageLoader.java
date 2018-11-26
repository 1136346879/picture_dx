package com.example.administrator.kotlintest.banner.mybanner.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexun on 2017/8/14.
 */

public class ImageLoader {

    private Picasso picasso;
    private RequestCreator requestCreator;
    private static ImageLoader imageLoader;

    private ImageLoader() {

    }

    public static ImageLoader with(Context context) {
        if (null == imageLoader) {
            imageLoader = new ImageLoader();
            imageLoader.picasso = Picasso.with(context);
        }
        return imageLoader;
    }

    public void invalidate(Uri uri) {
        picasso.invalidate(uri.toString());
    }

    /**
     * Invalidate all memory cached images for the specified {@code path}. You can also pass a
     * {@linkplain RequestCreator#stableKey stable key}.
     *
     * @see #invalidate(Uri)
     * @see #invalidate(File)
     */
    public void invalidate(String path) {
        invalidate(Uri.parse(path));
    }

    /**
     * Invalidate all memory cached images for the specified {@code file}.
     *
     * @see #invalidate(Uri)
     * @see #invalidate(String)
     */
    public void invalidate(File file) {
        invalidate(Uri.fromFile(file));
    }

    public ImageLoader load(Uri uri) {
        requestCreator = picasso.load(uri);
        return imageLoader;
    }

    /**
     * Start an image request using the specified path. This is a convenience method for calling
     * {@link #load(Uri)}.
     * <p>
     * This path may be a remote URL, file resource (prefixed with {@code file:}), content resource
     * (prefixed with {@code content:}), or android resource (prefixed with {@code
     * android.resource:}.
     * <p>
     * Passing {@code null} as a {@code path} will not trigger any request but will set a
     * placeholder, if one is specified.
     *
     * @throws IllegalArgumentException if {@code path} is empty or blank string.
     * @see #load(Uri)
     * @see #load(File)
     * @see #load(int)
     */
    public ImageLoader load(String path) {
        if (path == null || path.trim().length() == 0) {
            return load((Uri) null);
        }
        return load(Uri.parse(path));
    }

    /**
     * Start an image request using the specified image file. This is a convenience method for
     * calling {@link #load(Uri)}.
     * <p>
     * Passing {@code null} as a {@code file} will not trigger any request but will set a
     * placeholder, if one is specified.
     * <p>
     * Equivalent to calling {@link #load(Uri) load(Uri.fromFile(file))}.
     *
     * @see #load(Uri)
     * @see #load(String)
     * @see #load(int)
     */
    public ImageLoader load(File file) {
        return load(Uri.fromFile(file));
    }

    /**
     * Start an image request using the specified drawable resource ID.
     *
     * @see #load(Uri)
     * @see #load(String)
     * @see #load(File)
     */
    public ImageLoader load(int resourceId) {
        requestCreator = picasso.load(resourceId);
        return imageLoader;
    }

    public void cancelRequest(ImageView view) {
        picasso.cancelRequest(view);
    }

    public void cancelRequest(final Target target) {
        picasso.cancelRequest(new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (target != null) {
                    target.onBitmapLoaded(bitmap, LoadedFrom.getFrom(from));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (target != null) {
                    target.onBitmapFailed(errorDrawable);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (target != null) {
                    target.onPrepareLoad(placeHolderDrawable);
                }
            }
        });
    }

    public void cancelRequest(RemoteViews remoteViews, int viewId) {
        picasso.cancelRequest(remoteViews, viewId);
    }

    public ImageLoader noPlaceholder() {
        requestCreator.noPlaceholder();
        return imageLoader;
    }

    /**
     * A placeholder drawable to be used while the image is being loaded. If the requested image is
     * not immediately available in the memory cache then this resource will be set on the target
     * {@link ImageView}.
     */
    public ImageLoader placeholder(int placeholderResId) {
        requestCreator.placeholder(placeholderResId);
        return imageLoader;
    }

    /**
     * A placeholder drawable to be used while the image is being loaded. If the requested image is
     * not immediately available in the memory cache then this resource will be set on the target
     * {@link ImageView}.
     * <p>
     * If you are not using a placeholder image but want to clear an existing image (such as when
     * used in an {@link android.widget.Adapter adapter}), pass in {@code null}.
     */
    public ImageLoader placeholder(Drawable placeholderDrawable) {
        requestCreator.placeholder(placeholderDrawable);
        return imageLoader;
    }

    /**
     * An error drawable to be used if the request image could not be loaded.
     */
    public ImageLoader error(int errorResId) {
        requestCreator.error(errorResId);
        return imageLoader;
    }

    /**
     * An error drawable to be used if the request image could not be loaded.
     */
    public ImageLoader error(Drawable errorDrawable) {
        requestCreator.error(errorDrawable);
        return imageLoader;
    }

    /**
     * 如果用匿名内部类的传参方式，因为弱引用的关系，第一次可能不会回调loaded方法。
     * <p>
     * 解决方案：Store it in a field, a view tag, or implement on a view directly.
     * <p>
     * 来源：https://github.com/square/picasso/issues/625
     */
    private com.squareup.picasso.Target mTarget;

    public void into(final Target target) {
        mTarget = new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                if (target != null) {
                    target.onBitmapLoaded(bitmap, LoadedFrom.getFrom(from));
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                if (target != null) {
                    target.onBitmapFailed(errorDrawable);
                }
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (target != null) {
                    target.onPrepareLoad(placeHolderDrawable);
                }
            }
        };
        requestCreator.into(mTarget);
    }

    /**
     * Resize the image to the specified size in pixels.
     */
    public ImageLoader resize(int targetWidth, int targetHeight) {
        requestCreator.resize(targetWidth, targetHeight);
        return imageLoader;
    }

    /**
     * Attempt to decode the image using the specified config.
     * <p>
     * Note: This value may be ignored by {@link BitmapFactory}. See
     * {@link BitmapFactory.Options#inPreferredConfig its documentation} for more details.
     */
    public ImageLoader config(Bitmap.Config config) {
        requestCreator.config(config);
        return imageLoader;
    }

    public ImageLoader tag(Object tag) {
        requestCreator.tag(tag);
        return imageLoader;
    }

    public void cancelTag(Object tag) {
        picasso.cancelTag(tag);
    }

    public void pauseTag(Object tag) {
        picasso.pauseTag(tag);
    }

    public void resumeTag(Object tag) {
        picasso.resumeTag(tag);
    }

    public ImageLoader fit() {
        requestCreator.fit();
        return imageLoader;
    }

    public ImageLoader centerCrop() {
        requestCreator.centerCrop();
        return imageLoader;
    }

    public ImageLoader centerInside() {
        requestCreator.centerInside();
        return imageLoader;
    }

    public ImageLoader onlyScaleDown() {
        requestCreator.onlyScaleDown();
        return imageLoader;
    }

    /**
     * Rotate the image by the specified degrees.
     */
    public ImageLoader rotate(float degrees) {
        requestCreator.rotate(degrees);
        return imageLoader;
    }

    /**
     * Rotate the image by the specified degrees around a pivot point.
     */
    public ImageLoader rotate(float degrees, float pivotX, float pivotY) {
        requestCreator.rotate(degrees, pivotX, pivotY);
        return imageLoader;
    }

    /**
     * Add a custom transformation to be applied to the image.
     * <p>
     * Custom transformations will always be run after the built-in transformations.
     */
    // TODO show example of calling resize after a transform in the javadoc
    public ImageLoader transform(final Transformation transformation) {
        requestCreator.transform(new com.squareup.picasso.Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                if (transformation != null) {
                    return transformation.transform(source);
                }
                return source;
            }

            @Override
            public String key() {
                if (transformation != null) {
                    return transformation.key();
                }
                return null;
            }
        });
        return imageLoader;
    }

    public ImageLoader transform(List<? extends Transformation> transformations) {
        if (transformations == null || transformations.isEmpty()) {
            return this;
        }
        List<com.squareup.picasso.Transformation> list = new ArrayList<>(transformations.size());
        for (final Transformation t : transformations) {
            list.add(new com.squareup.picasso.Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    return t.transform(source);
                }

                @Override
                public String key() {
                    return t.key();
                }
            });
        }
        requestCreator.transform(list);
        return imageLoader;
    }

    public ImageLoader skipMemoryCache() {
        requestCreator.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE);
        return imageLoader;
    }

    public Bitmap get() throws IOException {
        return requestCreator.get();
    }

    public ImageLoader fetch() {
        return fetch(null);
    }

    public ImageLoader fetch(final Callback callback) {
        requestCreator.fetch(new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onError() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
        return imageLoader;
    }

    /**
     * Asynchronously fulfills the request into the specified {@link ImageView}.
     * <p>
     * <em>Note:</em> This method keeps a weak reference to the {@link ImageView} instance and will
     * automatically support object recycling.
     */
    public void into(ImageView target) {
        into(target, null);
    }

    /**
     * Asynchronously fulfills the request into the specified {@link ImageView} and invokes the
     * target {@link com.squareup.picasso.Callback} if it's not {@code null}.
     * <p>
     * <em>Note:</em> The {@link com.squareup.picasso.Callback} param is a strong reference and will prevent your
     * {@link android.app.Activity} or {@link android.app.Fragment} from being garbage collected. If
     * you use this method, it is <b>strongly</b> recommended you invoke an adjacent
     * {@link Picasso#cancelRequest(ImageView)} call to prevent temporary leaking.
     */
    public void into(ImageView target, final Callback callback) {
        requestCreator.config(Bitmap.Config.RGB_565).into(target, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onError() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    /**
     * Describes where the image was loaded from.
     */
    public enum LoadedFrom {
        MEMORY(Color.GREEN),
        DISK(Color.BLUE),
        NETWORK(Color.RED);

        final int debugColor;

        LoadedFrom(int debugColor) {
            this.debugColor = debugColor;
        }

        static LoadedFrom getFrom(Picasso.LoadedFrom from) {
            if (from == Picasso.LoadedFrom.MEMORY) {
                return MEMORY;
            } else if (from == Picasso.LoadedFrom.DISK) {
                return DISK;
            } else {
                return NETWORK;
            }
        }
    }

}
