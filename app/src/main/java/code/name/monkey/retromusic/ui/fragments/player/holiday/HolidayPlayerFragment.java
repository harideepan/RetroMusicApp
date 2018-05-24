package code.name.monkey.retromusic.ui.fragments.player.holiday;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import code.name.monkey.appthemehelper.ThemeStore;
import code.name.monkey.appthemehelper.util.TintHelper;
import code.name.monkey.appthemehelper.util.ToolbarContentTintHelper;
import code.name.monkey.retromusic.R;
import code.name.monkey.retromusic.helper.MusicPlayerRemote;
import code.name.monkey.retromusic.model.Song;
import code.name.monkey.retromusic.ui.fragments.base.AbsPlayerFragment;
import code.name.monkey.retromusic.ui.fragments.player.PlayerAlbumCoverFragment;

/**
 * @author Hemanth S (h4h13).
 */

public class HolidayPlayerFragment extends AbsPlayerFragment implements
        PlayerAlbumCoverFragment.Callbacks {
    @BindView(R.id.player_toolbar)
    Toolbar toolbar;
    @BindView(R.id.now_playing_container)
    ViewGroup viewGroup;
    @BindView(R.id.gradient_background)
    View background;

    private int lastColor;
    private Unbinder unBinder;
    private HolidayPlaybackControlsFragment playbackControlsFragment;

    private void setUpSubFragments() {
        playbackControlsFragment = (HolidayPlaybackControlsFragment)
                getChildFragmentManager().findFragmentById(R.id.playback_controls_fragment);

        PlayerAlbumCoverFragment playerAlbumCoverFragment = (PlayerAlbumCoverFragment)
                getChildFragmentManager().findFragmentById(R.id.player_album_cover_fragment);
        playerAlbumCoverFragment.setCallbacks(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_holiday_player, container, false);
        unBinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toggleStatusBar(viewGroup);

        TintHelper.setTintAuto(background, Color.WHITE, true);

        setUpSubFragments();
        setUpPlayerToolbar();
    }

    private void setUpPlayerToolbar() {
        toolbar.inflateMenu(R.menu.menu_player);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(this);

        ToolbarContentTintHelper.colorizeToolbar(toolbar, Color.WHITE, getActivity());
    }

    @Override
    @ColorInt
    public int getPaletteColor() {
        return lastColor;
    }


    @Override
    public void onShow() {
        playbackControlsFragment.show();
    }

    @Override
    public void onHide() {
        playbackControlsFragment.hide();
        onBackPressed();
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public int toolbarIconColor() {
        return Color.WHITE;
    }

    @Override
    public void onColorChanged(int color) {
        playbackControlsFragment.setDark(color);
        lastColor = color;
        getCallbacks().onPaletteColorChanged();
        ToolbarContentTintHelper.colorizeToolbar(toolbar, Color.WHITE, getActivity());
    }

    @Override
    protected void toggleFavorite(Song song) {
        super.toggleFavorite(song);
        if (song.id == MusicPlayerRemote.getCurrentSong().id) {
            updateIsFavorite();
        }
    }

    @Override
    public void onFavoriteToggled() {
        toggleFavorite(MusicPlayerRemote.getCurrentSong());
    }
}
