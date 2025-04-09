package com.heba.task_app.features.user.presentation.all_users

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heba.task_app.R
import com.heba.task_app.databinding.FragmentAllUsersBinding
import com.heba.task_app.core.presentation.delegation.HandleLoadingImpl
import com.heba.task_app.core.presentation.delegation.IHandlingLoading
import com.heba.task_app.features.user.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllUsersFragment : Fragment() ,
    IHandlingLoading by HandleLoadingImpl() {
    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllUserViewModel by viewModels()
    private lateinit var adapter: AllUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllUsersBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect {
                    onLoadingDialog(it, requireContext())
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllUsers.collect {
                    observeAllUsers(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect {
                    showAlertDialogWithoutAction(requireContext(), it , layoutInflater)
                }
            }
        }

        binding.apply {
            btnAddUser.setOnClickListener {
                findNavController().navigate(R.id.action_allUsersFragment_to_addUserFragment)
            }
        }
        viewModel.getAllUsers()
        setUpAllUsersAdapter()
        return binding.root
    }

    private fun setUpAllUsersAdapter() {
        adapter = AllUsersAdapter()
        binding.rvUsers.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.rvUsers.adapter = adapter
    }

    private fun observeAllUsers(user: List<User>?) {
        user?.let {
            binding.tvEmptyState.isVisible = it.isEmpty()
            adapter.submitList(it)
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