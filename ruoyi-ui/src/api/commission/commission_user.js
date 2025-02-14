import request from '@/utils/request'

// 查询用户管理列表
export function listCommission_user(query) {
  return request({
    url: '/commission/commission_user/list',
    method: 'get',
    params: query
  })
}

// 查询用户管理详细
export function getCommission_user(userId) {
  return request({
    url: '/commission/commission_user/' + userId,
    method: 'get'
  })
}

// 新增用户管理
export function addCommission_user(data) {
  return request({
    url: '/commission/commission_user',
    method: 'post',
    data: data
  })
}

// 修改用户管理
export function updateCommission_user(data) {
  return request({
    url: '/commission/commission_user',
    method: 'put',
    data: data
  })
}

// 删除用户管理
export function delCommission_user(userId) {
  return request({
    url: '/commission/commission_user/' + userId,
    method: 'delete'
  })
}
