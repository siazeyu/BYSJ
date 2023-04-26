import request from '@/utils/request'

// 查询学分申请列表
export function listCredit(query) {
  return request({
    url: '/credit/credit/getApproval',
    method: 'get',
    params: query
  })
}

// 修改学分申请
export function approvalCredit(data) {
  return request({
    url: '/credit/credit/approval',
    method: 'put',
    data: data
  })
}
