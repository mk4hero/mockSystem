// 查询报文下拉树结构
import request from "@/utils/request";

export function getMsgTreeSelect(params) {
  return request({
    url: '/mock/getTree',
    method: 'get',
    params: params
  })
}

// 查询报文下拉树结构
export function mappingList(params) {
  return request({
    url: '/mock/mapping/list',
    method: 'get',
    params: params
  })
}

export function addMapping(data) {
  return request({
    url: '/mock/mapping/add',
    method: 'post',
    data: data
  })
}

export function modifyMapping(data) {
  return request({
    url: '/mock/mapping',
    method: 'put',
    data: data
  })
}

export function delMapping(id) {
  return request({
    url: '/mock/mapping/' + id,
    method: 'delete'
  })
}
