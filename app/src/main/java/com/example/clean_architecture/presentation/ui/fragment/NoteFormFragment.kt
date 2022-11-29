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
import com.example.clean_architecture.R
import com.example.clean_architecture.databinding.FragmentNoteFormBinding
import com.example.clean_architecture.presentation.UIState
import kotlinx.coroutines.launch

class NoteFormFragment : Fragment() {

    private val formViewModel : NoteFormViewModel by viewModels()
    lateinit var binding: FragmentNoteFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentNoteFormBinding.inflate(layoutInflater)

        //formViewModel.createNote()

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                formViewModel.createNoteState.collect{state->
                    when(state){
                        is UIState.Loading->{

                        }
                        is UIState.Error->{
                            Toast.makeText(requireActivity(), state.message, Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Success->{

                        }
                        is UIState.Empty->{}
                    }
                }
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