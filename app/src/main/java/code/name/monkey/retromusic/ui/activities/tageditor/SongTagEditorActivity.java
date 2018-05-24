package code.name.monkey.retromusic.ui.activities.tageditor;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.jaudiotagger.tag.FieldKey;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import code.name.monkey.appthemehelper.ThemeStore;
import code.name.monkey.appthemehelper.util.ColorUtil;
import code.name.monkey.appthemehelper.util.TintHelper;
import code.name.monkey.retromusic.R;
import code.name.monkey.retromusic.loaders.SongLoader;


public class SongTagEditorActivity extends AbsTagEditorActivity implements TextWatcher {

    public static final String TAG = SongTagEditorActivity.class.getSimpleName();

    @BindView(R.id.title1)
    EditText songTitle;
    @BindView(R.id.title2)
    EditText albumTitle;
    @BindView(R.id.artist)
    EditText artist;
    @BindView(R.id.genre)
    EditText genre;
    @BindView(R.id.year)
    EditText year;
    @BindView(R.id.image_text)
    EditText trackNumber;
    @BindView(R.id.lyrics)
    EditText lyrics;
    @BindView(R.id.album_artist)
    EditText albumArtist;

    @BindView(R.id.menu_close)
    AppCompatImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setNoImageMode();
        setUpViews();
    }

    @OnClick(R.id.menu_close)
    void closeTagEditor() {
        onBackPressed();
    }

    private void setUpViews() {
        fillViewsWithFileTags();
        albumArtist.addTextChangedListener(this);
        songTitle.addTextChangedListener(this);
        albumTitle.addTextChangedListener(this);
        artist.addTextChangedListener(this);
        genre.addTextChangedListener(this);
        year.addTextChangedListener(this);
        trackNumber.addTextChangedListener(this);
        lyrics.addTextChangedListener(this);


        ColorStateList colorState = ColorStateList.valueOf(ColorUtil.withAlpha(ThemeStore.textColorPrimary(this), 0.2f));
        TintHelper.setTintAuto(close, Color.WHITE, false);
        close.setBackgroundTintList(colorState);
    }

    private void fillViewsWithFileTags() {
        songTitle.setText(getSongTitle());
        albumArtist.setText(getAlbumArtist());
        albumTitle.setText(getAlbumTitle());
        artist.setText(getArtistName());
        genre.setText(getGenreName());
        year.setText(getSongYear());
        trackNumber.setText(getTrackNumber());
        lyrics.setText(getLyrics());
    }

    @Override
    protected void loadCurrentImage() {

    }

    @Override
    protected void getImageFromLastFM() {

    }

    @Override
    protected void searchImageOnWeb() {

    }

    @Override
    protected void deleteImage() {

    }

    @Override
    protected void save() {
        Map<FieldKey, String> fieldKeyValueMap = new EnumMap<>(FieldKey.class);
        fieldKeyValueMap.put(FieldKey.TITLE, songTitle.getText().toString());
        fieldKeyValueMap.put(FieldKey.ALBUM, albumTitle.getText().toString());
        fieldKeyValueMap.put(FieldKey.ARTIST, artist.getText().toString());
        fieldKeyValueMap.put(FieldKey.GENRE, genre.getText().toString());
        fieldKeyValueMap.put(FieldKey.YEAR, year.getText().toString());
        fieldKeyValueMap.put(FieldKey.TRACK, trackNumber.getText().toString());
        fieldKeyValueMap.put(FieldKey.LYRICS, lyrics.getText().toString());
        fieldKeyValueMap.put(FieldKey.ALBUM_ARTIST, albumArtist.getText().toString());
        writeValuesToFiles(fieldKeyValueMap, null);
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_song_tag_editor;
    }

    @NonNull
    @Override
    protected List<String> getSongPaths() {
        ArrayList<String> paths = new ArrayList<>(1);
        paths.add(SongLoader.getSong(this, getId()).blockingFirst().data);
        return paths;
    }

    @Override
    protected void loadImageFromFile(Uri imageFilePath) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        dataChanged();
    }

    @Override
    protected void setColors(int color) {
        super.setColors(color);
    }

}
