import request from '@/utils/request'

// 查询Employee列表
export function listEmployee(query) {
  return request({
    url: '/Employee/employee/list',
    method: 'get',
    params: query
  })
}

// 查询Employee详细
export function getEmployee(employeeId) {
  return request({
    url: '/Employee/employee/' + employeeId,
    method: 'get'
  })
}

// 新增Employee
export function addEmployee(data) {
  return request({
    url: '/Employee/employee',
    method: 'post',
    data: data
  })
}

// 修改Employee
export function updateEmployee(data) {
  return request({
    url: '/Employee/employee',
    method: 'put',
    data: data
  })
}

// 删除Employee
export function delEmployee(employeeId) {
  return request({
    url: '/Employee/employee/' + employeeId,
    method: 'delete'
  })
}
