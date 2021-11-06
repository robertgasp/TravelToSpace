package com.example.traveltospace.ui


import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.*
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.example.traveltospace.POD.PODViewModel
import com.example.traveltospace.POD.retrofit.PODData
import com.example.traveltospace.R
import com.example.traveltospace.databinding.MainFragmentBinding
import com.squareup.picasso.Picasso
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import kotlinx.coroutines.delay
import kotlin.io.path.Path
import kotlin.io.path.moveTo


class PODFragment : Fragment() {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private var isExpanded = false

    private val viewModel: PODViewModel by lazy {
        ViewModelProviders.of(this).get(PODViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(requireActivity(), Observer<PODData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${inputEditText.text.toString()}")
            })
        }
    }

    override fun onStart() {
        super.onStart()
        slideWikiInputLayout(binding.inputLayout)
        fadeOutSomeObject(binding.wikiButton)
        zoomPOD()
    }


    private fun renderData(data: PODData) = with(binding) {
        when (data) {
            is PODData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {

                    Picasso
                        .get()
                        .load(url)
                        .fit()
                        .into(imageView)

                    title.text = serverResponseData.title
                    description.text = serverResponseData.explanation
                }
            }
            is PODData.Loading -> {
            }
            is PODData.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun slideWikiInputLayout(viewGroup:ViewGroup){
        TransitionManager.beginDelayedTransition(viewGroup,Slide(Gravity.END))
        viewGroup.visibility =View.VISIBLE
    }

/*    private fun slideWikiInputLayout(viewGroup:ViewGroup){

        val changeBounds = ChangeBounds()
        changeBounds.duration = 1000

        TransitionManager.beginDelayedTransition(viewGroup,TransitionSet()
            .addTransition(changeBounds)
            .addTransition(Slide(Gravity.START)))
        viewGroup.visibility =View.VISIBLE
    }*/

    private fun fadeOutSomeObject(view: View) {
        ObjectAnimator
            .ofFloat(view, View.ALPHA, 0f, 1f)
            .setDuration(2000)
            .start()
    }

    private fun zoomPOD() {
        binding.imageView.setOnClickListener {
            isExpanded = !isExpanded
            binding.podImageViewContainer.layoutParams.height = 1000
            binding.imageView.layoutParams.width = 1280
            binding.imageView.layoutParams.height = 1200

            TransitionManager.beginDelayedTransition(
                binding.podImageViewContainer, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            if (isExpanded) {
                binding.podImageViewContainer.layoutParams.height = 1000
                binding.imageView.layoutParams.width = 1280
                binding.imageView.layoutParams.height = 1200
            } else {
                binding.podImageViewContainer.layoutParams.height = 500
                binding.imageView.layoutParams.width = 500
                binding.imageView.layoutParams.height = 500
            }

            val params: ViewGroup.LayoutParams = binding.imageView.layoutParams
            params.height = if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else
                ViewGroup.LayoutParams.WRAP_CONTENT
            binding.imageView.layoutParams = params
            binding.imageView.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP else
                ImageView.ScaleType.FIT_CENTER
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }


    companion object {
        fun newInstance() = PODFragment()
        private var isMain = true
    }
}