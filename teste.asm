loop:
add $zero, $zero, $zero #incrementa $s0
slt $s2, $s3, $s0
beq $s2, $zero, loop