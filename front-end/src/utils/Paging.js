function paging (array, size) {
  const length = array.length
  const count = Math.floor(length / size) + (Math.floor(length % size) > 0 ? 1 : 0)
  const result = []

  for (let i = 0; i < count; i++) {
    result.push(array.splice(0, size))
  }

  return result
}

export default paging
