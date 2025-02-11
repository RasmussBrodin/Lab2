# From https://www.tutorialspoint.com/data_structures_algorithms/selection_sort_algorithm.htm
def sort(a):
    for i in range(len(a) - 1):
        minIndex = i
        for j in range(i+1, len(a)):
            if a[j] < a[minIndex]:
                minIndex = j
        a[i], a[minIndex] = a[minIndex], a[i]  
    return a

# From https://pseudoeditor.com/guides/binary-search 
def memberSorted(a, key):
    left = 0
    right = len(a) - 1

    while left <= right:
        mid = (left + right) // 2
        if a[mid] == key:
            return mid
        elif a[mid] < key:
            left = mid + 1
        else:
            right = mid - 1
    return -1

def memberUnsorted(a, key):
    sorted = sort(a)
    return memberSorted(sorted, key)


k = [2,10,4,3,7,1]
print(sort(k))
j = sort(k)
print(memberSorted(j, 4))
print(memberUnsorted(k,4))