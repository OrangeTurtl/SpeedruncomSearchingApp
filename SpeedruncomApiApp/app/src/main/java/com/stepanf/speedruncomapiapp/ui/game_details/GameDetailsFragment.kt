package com.stepanf.speedruncomapiapp.ui.game_details

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.style.BulletSpan
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.stepanf.speedruncomapiapp.R
import com.stepanf.speedruncomapiapp.data.details.SpeedrunDeveloper
import com.stepanf.speedruncomapiapp.data.details.SpeedrunGenre
import com.stepanf.speedruncomapiapp.data.details.SpeedrunPlatform
import com.stepanf.speedruncomapiapp.data.details.SpeedrunRegion
import com.stepanf.speedruncomapiapp.databinding.FragmentGameDetailsBinding
import com.stepanf.speedruncomapiapp.databinding.FragmentGamesSearchBinding
import com.stepanf.speedruncomapiapp.ui.games_search.GamesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

private const val TAG = "GameDetailsFragment"

@AndroidEntryPoint
class GameDetailsFragment : Fragment(R.layout.fragment_game_details) {

    private val args by navArgs<GameDetailsFragmentArgs>()
    private val viewModel by viewModels<GameDetailsViewModel>()

    private var _binding: FragmentGameDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGameDetailsBinding.bind(view)

        binding.apply {
            val game = args.game

            textViewGameName.text = game.names.international
            textViewReleaseDate.text = game.release_date


            viewModel.initialize(game)
            viewModel.platforms.observe(viewLifecycleOwner) { platforms ->
               updateInformationPlatforms(platforms)
            }
            viewModel.genres.observe(viewLifecycleOwner) { genre ->
                updateInformationGenres(genre)
            }
            viewModel.regions.observe(viewLifecycleOwner) { regions ->
                updateInformationRegions(regions)
            }
            viewModel.developers.observe(viewLifecycleOwner) { developers ->
                updateInformationDevelopers(developers)
            }

            //Load game image
            Glide.with(this@GameDetailsFragment)
                .load(game.assets.cover_medium.uri)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarImage.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBarImage.isVisible = false
                        return false
                    }
                })
                .into(imageView)

            val uri = Uri.parse(game.weblink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            buttonSpeedruncomLink.apply {
                setOnClickListener {
                    context.startActivity(intent)
                }
                buttonSpeedruncomLink.paint.isUnderlineText = true
            }
        }
    }

    private fun updateInformationPlatforms(platforms: List<SpeedrunPlatform>) {
        binding.apply {
            textViewTitlePlatform.isVisible = true
            textViewGamePlatforms.text = ""
            platforms.forEach {
                textViewGamePlatforms.append("${it.name}\n")
            }
        }
    }

    private fun updateInformationGenres(genres: List<SpeedrunGenre>) {
        binding.apply {
            textViewTitleGenre.isVisible = true
            textViewGameGenres.text = ""
            genres.forEach {
                textViewGameGenres.append("${it.name}\n")
            }
        }
    }

    private fun updateInformationRegions(regions: List<SpeedrunRegion>) {
        binding.apply {
            textViewTitleRegion.isVisible = true
            textViewGameRegions.text = ""
            regions.forEach {
                textViewGameRegions.append("${it.name}\n")
            }
        }
    }

    private fun updateInformationDevelopers(developers: List<SpeedrunDeveloper>) {
        binding.apply {
            textViewTitleDeveloper.isVisible = true
            textViewGameDevelopers.text = ""
            developers.forEach {
                textViewGameDevelopers.append("${it.name}\n")
            }
        }
    }
}