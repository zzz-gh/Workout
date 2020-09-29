package com.example.workout_app

class Constants {
    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModel> {

            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks =
                ExerciseModel(1, "Jumping Jacks", R.drawable.jumping_jacks, false, false)
            exerciseList.add(jumpingJacks)



            val wallSit = ExerciseModel(2, "Wall Sit", R.drawable.wall_sit, false, false)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3, "Squat Exercise", R.drawable.squat, false, false)
            exerciseList.add(pushUp)

            val abdominalCrunch =
                ExerciseModel(4, "Abdominal Crunch", R.drawable.standing_crunches, false, false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                ExerciseModel(
                    5,
                    "Side Lunge",
                    R.drawable.side_lunge,
                    false,
                    false
                )
            exerciseList.add(stepUpOnChair)

            val squat = ExerciseModel(6, "Push up", R.drawable.push_up, false, false)
            exerciseList.add(squat)

            val tricepDipOnChair =
                ExerciseModel(
                    7,
                    "Rope Jumping",
                    R.drawable.rope_jumping,
                    false,
                    false
                )
            exerciseList.add(tricepDipOnChair)

            val plank = ExerciseModel(8, "Long Lean Tones", R.drawable.long_lean_tone, false, false)
            exerciseList.add(plank)

            val highKneesRunningInPlace =
                ExerciseModel(
                    9, "Strengthen Exercise",
                    R.drawable.strengteh_exercise,
                    false,
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExerciseModel(10, "Sleep sexy tight exercise", R.drawable.sleep_sexy_tight_exercise, false, false)
            exerciseList.add(lunges)

            val pushupAndRotation =
                ExerciseModel(
                    11,
                    "Sumo side bands",
                    R.drawable.sumo_side_bands,
                    false,
                    false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExerciseModel(12, "Slim tight exercise", R.drawable.slim_tight_exercise, false, false)
            exerciseList.add(sidePlank)

            return exerciseList
        }



    }
}