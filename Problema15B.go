package main

import "fmt"

func main() {
	multa := 16807
	multb := 48271
	div := 2147483647
	count := 0
	a := 703 * multa
	b := 516 * multb
	waitForA := true
	waitForB := true
	aIt := 0
	bIt := 0
	for aIt < 5000000 || bIt < 50000 {
		if waitForA && a%4 == 0 {
			waitForA = false
			aIt++
		}
		if waitForB && b%8 == 0 {
			waitForB = false
			bIt++
		}
		if waitForA && !waitForB {
			a = (a * multa) % div
			continue
		}
		if waitForB && !waitForA {
			b = (b * multb) % div
			continue
		}
		if waitForA && waitForB {
			a = (a * multa) % div
			b = (b * multb) % div
			continue
		}
		if !waitForA && !waitForB {
			//fmt.Printf("gen a: %d gen b: %d", a, b)
			resa := a & 0xffff
			resb := b & 0xffff
			resfinal := resa ^ resb
			if resfinal == 0 {
				count++
			}

			a = (a * multa) % div
			b = (b * multb) % div
			waitForA = true
			waitForB = true
		}
	}
	fmt.Printf("Solution is: %v ", count)

}
