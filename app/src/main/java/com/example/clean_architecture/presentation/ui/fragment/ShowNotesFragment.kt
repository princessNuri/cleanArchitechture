package com.example.clean_architecture.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clean_architecture.R
import com.example.clean_architecture.databinding.FragmentNoteFormBinding
import com.example.clean_architecture.databinding.FragmentShowNotesBinding
import com.example.clean_architecture.databinding.ItemNoteBinding
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.presentation.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowNotesFragment : BaseFragment(R.layout.fragment_show_notes) {

    private val showViewModel : ShowNotesViewModel by viewModels()
    private val binding :FragmentShowNotesBinding by viewBinding(FragmentShowNotesBinding::bind)
    private val _binding :ItemNoteBinding by viewBinding(ItemNoteBinding::bind)
    override fun initialize() {}
    override fun setUpRequests() {}
    override fun setUpObservers() {}
    override fun setUpClickListeners(
    ) {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showViewModel.getAllNotes()

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                showViewModel.getAllNotesState.collectState(
                    onLoading = {
                        //TODO show progress bar
                    },
                    onError = {message->
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()

                    },
                    onSuccess = {

                    }
                )
            }
        }

        showViewModel.deleteNote(
            Note(
            title = _binding.itemTitle.toString(),
            description = _binding.itemDescription.toString(),
            createdAt = System.currentTimeMillis()
        )
        )

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                showViewModel.deleteNoteState.collectState(
                    onLoading = {
                        //TODO show progress bar
                    },
                    onError = {message->
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()

                    },
                    onSuccess = {
                        Toast.makeText(requireActivity(), "Successfully deleted!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_notes, container, false)

    }

}