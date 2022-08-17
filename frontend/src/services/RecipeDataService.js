import http from '../http-common'

class RecipeDataService {
    getAll() {
        return http.get('readAll')
    }

    get(id) {
        return http.get(`readById/${id}`)
    }

    create(data) {
        return http.post('/create/', data)
    }

    update(id, data) {
        return http.put(`/update/${id}`, data)
    }

    delete(id) {
        return http.delete(`/delete/${id}`)
    }
}

export default new RecipeDataService()
