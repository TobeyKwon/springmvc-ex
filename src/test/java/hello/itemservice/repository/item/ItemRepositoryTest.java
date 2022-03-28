package hello.itemservice.repository.item;

import hello.itemservice.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 15);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> list = itemRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void updateItem() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        assertThat(itemRepository.findById(itemId).getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(itemRepository.findById(itemId).getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(itemRepository.findById(itemId).getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}