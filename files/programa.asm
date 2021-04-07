#incrementa $t3 até que seja igual a $s3
loop: add $t3, $t3, $s1         #$t3=0     	    $s1 = 1
slt $t4, $t3,$s3                #$t4 = 0     	$s3 = 3
beq $t4, $s1, loop #teste

#Tipo I
loop2:sw $t3, 4($t2)      #$s2 = 2        $t2 = 10010000
lw $t3, 4($t2)      #$t3 = 00000000 $t2 = 10010000

#Tipo R
#verifica se s1 é menor do que s2
slt $s3, $s1,$s2        #$s3 = 3             $s1 = 1 = 0001      $s2 = 2 = 0010
and $s4, $s1, $s2       #$s4 = 4             $s1 = 1 = 0001      $s2 = 2 = 0010
or $s5,  $s1, $s2       #$s5 = 5             $s1 = 1 = 0001      $s2 = 2 = 0010
sub $s6, $s2, $s1       #$s6 = 6             $s1 = 1 = 0001      $s2 = 2 = 0010
add $s7, $s1, $s2       #$s7 = 7             $s1 = 1 = 0001      $s2 = 2 = 0010

# pula para o inicio do programa
j loop2



