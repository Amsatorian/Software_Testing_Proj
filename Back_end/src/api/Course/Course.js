import request from '@/utils/request'

// 查询Course列表
export function listCourse(query) {
  return request({
    url: '/Course/Course/list',
    method: 'get',
    params: query
  })
}

// 查询Course详细
export function getCourse(id) {
  return request({
    url: '/Course/Course/' + id,
    method: 'get'
  })
}

// 新增Course
export function addCourse(data) {
  return request({
    url: '/Course/Course',
    method: 'post',
    data: data
  })
}

// 修改Course
export function updateCourse(data) {
  return request({
    url: '/Course/Course',
    method: 'put',
    data: data
  })
}

// 删除Course
export function delCourse(id) {
  return request({
    url: '/Course/Course/' + id,
    method: 'delete'
  })
}
