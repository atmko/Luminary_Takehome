/*
 * Copyright (C) 2019 Aayat Mimiko
 */

package cvdevelopers.takehome.utils.executors;

import java.util.concurrent.Executor;

class AppExecutors(val diskIO: Executor,
                   val mainThread: Executor)
