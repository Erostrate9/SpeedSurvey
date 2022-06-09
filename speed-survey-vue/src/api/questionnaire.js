import request from '@/utils/request'

const questionnaireApi = {
    GetAllInPublic: '/api/questionnaire/queryAllInPublic',
    GetDetail: '/api/questionnaire/queryDetail'
}

export function getAllInPublic() {
    return request({
        url: questionnaireApi.GetAllInPublic,
        method: 'get'
    })
}
export function getDetail(parameter) {
    return request({
        url: questionnaireApi.GetDetail,
        method: 'get',
        params: parameter,
    })
}