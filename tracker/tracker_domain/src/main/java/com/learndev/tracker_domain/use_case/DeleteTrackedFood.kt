package com.learndev.tracker_domain.use_case

import com.learndev.tracker_domain.model.TrackedFood
import com.learndev.tracker_domain.repository.TrackerRepository
class DeleteTrackedFood(private val repository: TrackerRepository) {
    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        repository.deleteTrackedFood(food = trackedFood)
    }
}