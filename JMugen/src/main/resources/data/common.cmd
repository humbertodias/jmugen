[Command]
name = "pressup"
command = $U
time = 1


[StatedefContinue -1]


[state -1,guard to 0]
type = changestate
trigger1 = prevstateno = 140 || prevstateno = 130
trigger1 = stateno = 140 && animtime = 0
trigger1 = ctrl
value = 0
trigger1 = hitover
				
[State -1,]
type = changestate
triggerall = command != "holdfwd" && command != "holdback"
trigger1 = stateno != 50 && stateno != 40
trigger1 = time > 1 && movetype = I && statetype = S && physics = S
trigger1 = animtime = 0 || 20 = stateno
trigger1 = ctrl
value = 0
trigger1 = hitover

[state 20, 4]
type = changestate
triggerall = stateno = 20 && commandCount = 0
trigger1 = floor(vel x) =0
trigger1 = vel x > 0
trigger2 = ceil(vel x) =0
trigger2 = vel x < 0
value = 0
trigger1 = hitover

[State -1,]
type = changestate
value = 20
triggerall = command = "holdfwd" && command != "holddown" && command != "holdup" && (!isassertspecial(nowalk))
trigger1 = ctrl
trigger1 = movetype = I
trigger1 = stateno < 120 || stateno > 155
trigger1 = statetype = S || statetype = C
trigger1 = stateno != 100 && stateno != 105 && stateno != 106 && stateno != 110 && stateno != 115
;trigger1 = stateno = 0
trigger1 = hitover

[State -1,]
type = changestate
value = 20
triggerall = command = "holdback" && command != "holddown" && command != "holdup" && (!isassertspecial(nowalk))
trigger1 = ctrl
trigger1 = stateno < 120 || stateno > 155
trigger1 = movetype = I
trigger1 = statetype = S || statetype = C
trigger1 = stateno != 100 && stateno != 105 && stateno != 106 && stateno != 110 && stateno != 115
;trigger1 = stateno = 0
trigger1 = hitover

[State -1,]
type = changestate
value = 10
triggerall = command = "holddown"
trigger1 = ctrl
trigger1 = statetype = S
trigger1 = hitover


[State -1,]
type = changestate
value = 12
triggerall = command != "holddown"
trigger1 = ctrl
trigger1 = stateno = 11
trigger1 = hitover





[state -1,]
type = spritebeanset
bean = info.airjump.num
value = const(movement.airjump.num)
trigger1 = stateno = 40
trigger1 = hitover

[state -1,]
type = changestate
value = 40
triggerall = command = "holdup"
triggerall = statetype = S
triggerall = physics != A
triggerall = command != "holddown"
trigger1 = ctrl
trigger1 = stateno != 50
trigger1 = hitover

[state -1,]
type = spritebeanset
bean = info.airjump.num
value = beaninfo(info.airjump.num) - 1
triggerall = command = "pressup"
triggerall = statetype = A
;triggerall = physics = A
triggerall = command != "holddown"
trigger1 = pos y < -const(movement.airjump.height)
trigger1 = stateno != 45
;trigger1 = beaninfo(info.airjump.num)  > 0
trigger1 = hitover

[state -1,]
type = changestate
value = 45
triggerall = command = "pressup"
triggerall = statetype = A
;triggerall = physics = A
triggerall = command != "holddown"
trigger1 = pos y < -const(movement.airjump.height)
trigger1 = stateno != 45
trigger1 = beaninfo(info.airjump.num)  >= 0
trigger1 = hitover
