# BukkitRunnableHelper

BukkitRunnableHelper is a small collection of interfaces and classes (in java) which makes it easier to schedule tasks in the Bukkit Scheduler. Only a small setup is needed and all your classes in Bukkit which need to be scheduled are easily implemented.

The required steps to get this to work are:

  1. At the start (e.g. onEnable method in JavaPlugin) do: ```PlannableHandler.setPlugin(this);```
  2. Now you have 4 options:
    * ```implements TimeOutable``` (for one-time effects after a certain timeout)
    * ```implements Repeatable``` (for repeated effects until canceled)
    * ```implements TimedRepeatable``` (for repeated effects which cancels after a certain time)
    * ```implements ConditionalRepeatable``` (for repeated effects which cancel after a certain condition is met)
  3. After implementing one of the above interfaces in your class (and implementing the necessary methods), do: ```PlannableHandler.handlePlannable(this)``` in your if you want to schedule the effect.
  
  That's it!
  
  More information can be found on my [page](pieter2406.github.io)

  Maven importing functionality through JitPack:
  ![JitPack](https://img.shields.io/github/tag/Pieter2406/BukkitRunnableHelper.svg?label=JitPack)[](https://jitpack.io/#Pieter2406/BukkitRunnableHelper/5508bbef8a)
