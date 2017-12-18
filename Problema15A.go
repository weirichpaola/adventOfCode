package main

import "fmt"

func main() {
	multa := 16807
	multb := 48271
	div := 2147483647
	count := 0
	a := 703 * multa
	b := 516 * multb

	for i := 0; i < 5000000; i++ {
		if a%4 == 0 && b%8 == 0 {
			resa := a & 0xffff
			resb := b & 0xffff
			resfinal := resa ^ resb
			if resfinal == 0 {
				count++
			}
		}
		a = (a * multa) % div
		b = (b * multb) % div

	}
	fmt.Printf("Solution is: %v ", count)

}
