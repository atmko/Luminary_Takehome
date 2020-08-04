package cvdevelopers.takehome.view.clientlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cvdevelopers.githubstalker.databinding.FragmentClientListBinding
import cvdevelopers.takehome.view.common.BaseFragment

/**
 * Fragment to display client list
 */
class ClientListFragment : BaseFragment() {

    private var _binding: FragmentClientListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPresentationComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentClientListBinding.inflate(inflater, container, false)
        return binding.root
    }
}