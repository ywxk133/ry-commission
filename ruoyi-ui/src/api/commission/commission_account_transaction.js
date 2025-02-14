import request from '@/utils/request'

// 查询余额流水列表
export function listCommission_account_transaction(query) {
  return request({
    url: '/commission/commission_account_transaction/list',
    method: 'get',
    params: query
  })
}

// 查询余额流水详细
export function getCommission_account_transaction(transactionId) {
  return request({
    url: '/commission/commission_account_transaction/' + transactionId,
    method: 'get'
  })
}

// 新增余额流水
export function addCommission_account_transaction(data) {
  return request({
    url: '/commission/commission_account_transaction',
    method: 'post',
    data: data
  })
}

// 修改余额流水
export function updateCommission_account_transaction(data) {
  return request({
    url: '/commission/commission_account_transaction',
    method: 'put',
    data: data
  })
}

// 删除余额流水
export function delCommission_account_transaction(transactionId) {
  return request({
    url: '/commission/commission_account_transaction/' + transactionId,
    method: 'delete'
  })
}
