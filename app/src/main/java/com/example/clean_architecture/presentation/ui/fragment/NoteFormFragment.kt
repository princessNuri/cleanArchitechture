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
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.clean_architecture.R
import com.example.clean_architecture.databinding.FragmentNoteFormBinding
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.presentation.UIState
import kotlinx.coroutines.launch

class NoteFormFragment : BaseFragment(R.layout.fragment_note_form) {

    private val formViewModel : NoteFormViewModel by viewModels()
    private val binding: FragmentNoteFormBinding by viewBinding(FragmentNoteFormBinding::bind)
    override fun initialize() {}
    override fun setUpRequests() {}
    override fun setUpObservers() {}
    override fun setUpClickListeners() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSave.setOnClickListener {
            formViewModel.createNote(
                Note(
                    title = binding.etTitle.toString(),
                    description = binding.etDescription.toString(),
                    createdAt = System.currentTimeMillis()
                )
            )
        }

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                formViewModel.createNoteState.collectState(
                    onLoading = {
                        //TODO show progress bar
                    },
                    onError = {message->
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                    },
                    onSuccess = {
                        Toast.makeText(requireActivity(), "Successfully created!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        formViewModel.editNote(
            Note(
                title = binding.etTitle.toString(),
                description = binding.etDescription.toString(),
                createdAt = System.currentTimeMillis()
            )
        )

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                formViewModel.editNoteState.collectState(
                    onLoading = {
                        //TODO show progress bar
                    },
                    onError = {message->
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
                    },
                    onSuccess = {
                        Toast.makeText(requireActivity(), "Successfully edited!", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                )
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_form, container, false)

    }
}