package com.heba.task_app.features.user.presentation.add_user

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.heba.task_app.R
import com.heba.task_app.databinding.FragmentAddUserBinding
import com.heba.task_app.core.presentation.delegation.HandleLoadingImpl
import com.heba.task_app.core.presentation.delegation.IHandlingLoading
import com.heba.task_app.features.user.data.local.entities.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddUserFragment : Fragment() ,
    IHandlingLoading by HandleLoadingImpl() {
    private var _binding: FragmentAddUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUserBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigate.collect {
                    Toast.makeText(requireContext(),
                        getString(R.string.user_added_successfully), Toast.LENGTH_SHORT)
                        .show()
                   findNavController().navigateUp()
                }
            }
        }

        binding.apply {
            btnAddUser.setOnClickListener {
                if (validateInputs()) {
                    viewModel.addUser(
                        UserEntity(
                            userName = edtUserName.text.toString(),
                            userAge = edtUserAge.text.toString().toInt(),
                            userJob = edtUserJob.text.toString(),
                            userGender = edtUserGender.text.toString()
                        )
                    )
                }
            }
        }
        return binding.root
    }

    private fun validateInputs(): Boolean {
        if (binding.edtUserName.text.toString().isEmpty()) {
            showAlertDialogWithoutAction(
                requireContext(),
                getString(R.string.you_must_enter_user_name),
                layoutInflater
            )
            return false
        } else if (binding.edtUserAge.text.toString().isEmpty()) {
            showAlertDialogWithoutAction(
                requireContext(),
                getString(R.string.you_must_enter_user_age),
                layoutInflater
            )
            return false
        } else if (binding.edtUserJob.text.toString().isEmpty()) {
            showAlertDialogWithoutAction(
                requireContext(),
                getString(R.string.you_must_enter_user_job_title),
                layoutInflater
            )
            return false
        } else if (binding.edtUserGender.text.toString().isEmpty()) {
            showAlertDialogWithoutAction(
                requireContext(),
                getString(R.string.you_must_enter_user_gender),
                layoutInflater
            )
            return false
        } else {
            return true
        }
    }
    private fun showAlertDialogWithoutAction(
        context: Context,
        message: String,
        layoutInflater: LayoutInflater
    ) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_alert, null)
        val okButton = dialogLayout.findViewById<Button>(R.id.btnOk)
        val textMessage = dialogLayout.findViewById<TextView>(R.id.tvMessage)

        val builder = AlertDialog.Builder(context).setView(dialogLayout).create()
        builder?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder?.setCancelable(false)

        textMessage.text = message

        okButton.setOnClickListener {
            builder.dismiss()
        }

        builder.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}