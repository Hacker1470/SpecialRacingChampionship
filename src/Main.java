import data.CatalogOfEmployees;
import game.GameSession;
import data.CatalogOfParts;

void main() {
    GameSession gm = new GameSession(10000, 1000);
    CatalogOfParts.catalogInit();
    CatalogOfEmployees.catalogInit();
    gm.launch();

    //Заметки на будущее
    //Мне ведь надо как-то идентифицировать каждую деталь и человека на складе и в общаге, чтобы я мог изменять
    //их характеристики.
    //Предлагаю при покупке в айдишник добавлять им порядковый номер. Когда интеджер дойдёт до своего края,
    //нумерация начнётся заново, причём должна быть ещё проверка на то, чтобы айдишники не повторялись.
    //Либо может в класс добавить отдельное поля, я не знаю
}
