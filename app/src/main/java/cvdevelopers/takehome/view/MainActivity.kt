package cvdevelopers.takehome.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.dagger.presentation.PresentationModule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as LuminaryTakeHomeApplication)
                .appComponent
                .newPresentationComponent(PresentationModule())
                .inject(this)

    }

}
