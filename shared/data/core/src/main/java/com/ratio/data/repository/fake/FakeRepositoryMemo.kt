package com.ratio.data.repository.fake

import com.ratio.base.TestDispatchersProvider
import com.ratio.data.DataObserver
import com.ratio.data.repository.RepositoryMemoFactory
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
fun fakeRepositoryMemoFactory(): RepositoryMemoFactory = RepositoryMemoFactory(
    dataObserver = DataObserver(),
    dispatchers = TestDispatchersProvider
)