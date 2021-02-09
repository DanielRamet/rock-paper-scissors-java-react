
const GAME_REST_API_URL = 'http://localhost:8080/api/game';

class GameService{

    getAllItems(){
        return fetch(GAME_REST_API_URL + '/all-items').then(response => response.json());
    }

    getResume(){
        return fetch(GAME_REST_API_URL + "/resume").then(response => response.json());
    }

    playRoundPost(player1, player2){
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ player1Choice: player1, player2Choice: player2 })
        };
        return fetch(GAME_REST_API_URL + "/round", requestOptions).then(response => response.json());
    }
}

export default new GameService();