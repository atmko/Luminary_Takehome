package cvdevelopers.takehome.view.clientlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.view.common.BaseFragment

/**
 * Fragment to display client list
 */
class ClientListFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPresentationComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_list, container, false)
    }
}