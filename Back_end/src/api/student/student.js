import request from '@/utils/request'

// 查询student列表
export function listStudent(query) {
  return request({
    url: '/student/student/list',
    method: 'get',
    params: query
  })
}

// 查询student详细
export function getStudent(id) {
  return request({
    url: '/student/student/' + id,
    method: 'get'
  })
}

// 新增student
export function addStudent(data) {
  return request({
    url: '/student/student',
    method: 'post',
    data: data
  })
}

// 修改student
export function updateStudent(data) {
  return request({
    url: '/student/student',
    method: 'put',
    data: data
  })
}

// 删除student
export function delStudent(id) {
  return request({
    url: '/student/student/' + id,
    method: 'delete'
  })
}
