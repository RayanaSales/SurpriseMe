package ifpe.surpriseme.flickrApi;


import ifpe.surpriseme.activities.BackgroundActivity.UIHandler;
import ifpe.surpriseme.models.ImageContener;

public interface IThumb {
    public void onSaveThumbURL(UIHandler uih, ImageContener ic);
}