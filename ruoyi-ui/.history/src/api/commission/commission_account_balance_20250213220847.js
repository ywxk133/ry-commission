import request from '@/utils/request'

// 查询用户余额列表
export function listCommission_account_balance(query) {
  return request({
    url: '/commission/commission_account_balance/list',
    method: 'get',
    params: query
  })
}

// 查询用户余额详细
export function getCommission_account_balance(userId) {
  return request({
    url: '/commission/commission_account_balance/' + userId,
    method: 'get'
  })
}

// 新增用户余额
export function addCommission_account_balance(data) {
  return request({
    url: '/commission/commission_account_balance',
    method: 'post',
    data: data
  })
}

// 修改用户余额
export function updateCommission_account_balance(data) {
  return request({
    url: '/commission/commission_account_balance',
    method: 'put',
    data: data
  })
}

// 删除用户余额
export function delCommission_account_balance(userId) {
  return request({
    url: '/commission/commission_account_balance/' + userId,
    method: 'delete'
  })
}
// 增加用户余额
export function increaseBalance(data) {
  return request({
    url: '/commission/increase' ,
    method: 'post',
    data: data
  })
}
// //减少用户余额
// export function increaseBalance(data) {
//   return request({
//     url: '/commission/decrease' ,
//     method: 'post',
//     data: data
//   })
// }