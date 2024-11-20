package ru.ucheba.hw1.repository

import android.content.Context
import androidx.core.content.ContextCompat
import ru.ucheba.hw1.R
import ru.ucheba.hw1.model.FirstHolderData
import ru.ucheba.hw1.model.MultipleHoldersData
import ru.ucheba.hw1.model.SecondHolderData

object ScreensContentRepository {
    val headers = listOf(
        "Alya Gubkina",
        "Sasha Chesnok",
        "Mega Ilya",
        "Super zhziza",
        "Meme Katya",
        "Sasha",
        "Lyoha",
        "Ildar Sharov",
        "Hossam Hulderov",
        "Hammad Hussainov",
        "Galya",
        "Vova Sen Armat",
        "Vanek Rebov",
        "Mister X",
        "Sonechka Marmeladova",
        "Garam Hudin",
        "Miss Church",
        "Garim",
        "Zarim Gubkin",
        "Amily Hariv",
        "Amanda Vasil"
    )
    val imageUrls = listOf(
        "https://avatars.mds.yandex.net/i?id=f0a5ba9dd19e16e10d8e68449152404fb183304a-10261390-images-thumbs&n=13",
        "https://avatars.mds.yandex.net/i?id=87e233999b91de828441ccc0b8368401c7396d16-9203527-images-thumbs&n=13",
        "https://cs11.pikabu.ru/post_img/2019/04/13/10/og_og_1555173761275936871.jpg",
        "https://i.pinimg.com/originals/99/58/2c/99582c64bdf9c3db1dfd90ee71790213.jpg",
        "https://pic.rutubelist.ru/video/b9/44/b9447259fffe4d88e7044e3cc7193f5a.jpg",
        "https://img0.liveinternet.ru/images/attach/c/0/46/146/46146748_paris.jpg",
        "https://a.d-cd.net/b8432b8s-960.jpg",
        "https://avatars.mds.yandex.net/i?id=d6a1bf15039a67fb58498fd161a2c746_l-5438397-images-thumbs&n=13",
        "https://avatars.mds.yandex.net/i?id=0828c5e2be82aa35c06a4d1489a9476e_l-7016539-images-thumbs&n=13",
        "https://sun9-38.userapi.com/impf/c629116/v629116633/16f7d/4EuaKFduWs8.jpg?size=604x415&quality=96&sign=b8d197eaf19600aa8097ec7860d24557",
        "https://avatars.mds.yandex.net/i?id=4c11672f879dde200012674732b2447a_l-5427846-images-thumbs&n=13",
        "https://avatars.mds.yandex.net/get-mpic/1514097/img_id3127676746969410952.jpeg/orig",
        "https://avatars.mds.yandex.net/i?id=d0c453542e9bc2d83883f6cf49564e4a_l-4902040-images-thumbs&n=13",
        "https://i.pinimg.com/736x/ba/4a/03/ba4a03802f511a47e867701f26efb1ec.jpg",
        "https://i.pinimg.com/736x/3f/5a/95/3f5a95a5998632393c20f683e2d0d223.jpg",
        "https://avatars.mds.yandex.net/i?id=b61acc53f94f94cf05ce7882de4689a9_l-4774101-images-thumbs&n=13",
        "https://avatars.mds.yandex.net/i?id=14441c5297d491604768c4b5834accfb3c483059-9856874-images-thumbs&n=13",
        "https://sun9-55.userapi.com/c855024/v855024159/1e889f/6CdMpGrN5_U.jpg",
        "https://main-cdn.sbermegamarket.ru/big2/hlr-system/1480143217/600001113501b0.jpeg",
        "https://avatars.mds.yandex.net/i?id=9bf346fbb5ccc895dfbcb8175133d6cc_l-5231934-images-thumbs&n=13",
        "https://sun9-13.userapi.com/c5831/u137249437/-14/x_116b5487.jpg"
    )

    val desc = listOf(
        "19 лет, успешный бизнесмен. Никакие вопросы (кроме финансовых) не интересуют",
        "18 лет. Фанат шрека",
        "Самый мощный человек",
        "Master of wind",
        "Донатер в игрульки пишу стихи крутой классный",
        "Продам гаража писать в лс цена недорого от 1770000 в Москве",
        "No description",
        "No description",
        "No description",
        "Цените родную матушку она у вас одна!!!",
        "Чемпион по боксу",
        "За Русь стоять будем и еще за что-то не помню",
        "Люблю жимолость",
        "Пам-пам-пам описание пампам",
        "Меня никто не любит...",
        "Лучшая защита -- это нападение",
        "Боксирую с семнадцати лет",
        "Фанатею от БТС и марвел",
        "Крутой чувак",
        "Зал качалка штанга бокс",
        "Зигзаг провернул, петлял как мог по улице Москвы"
    )
    //Preloaded List

    fun getListForMultipleTypes(): List<MultipleHoldersData> = mutableListOf(
        FirstHolderData(
            id = "1",
            headerText = "Button",
        ),
        SecondHolderData(
            id = "2",
            headerText = "Maya",
            imageUrl = "https://avatars.mds.yandex.net/i?id=c7c24a293c67e134037ba9ac6a161b21_l-7084983-images-thumbs&n=13",
            descText = "I wear gucci and prada i am SO COOL"
        ),
        SecondHolderData(
            id = "3",
            headerText = "Mr Crabs",
            imageUrl = "https://cs4.pikabu.ru/post_img/2016/05/20/3/og_og_1463716746219174352.jpg",
            descText = "Профи во всем"
        ),
        SecondHolderData(
            id = "4",
            headerText = "Harim han",
            imageUrl = "https://i.pinimg.com/736x/d0/63/86/d06386e72d05a6b86836268db09768a2.jpg",
            descText = "Я из Африки"
        ),
        SecondHolderData(
            id = "5",
            headerText = "Goochi Ganger",
            imageUrl = "https://99px.ru/sstorage/53/2011/09/tmb_22863_1980.jpg",
            descText = "Люблю ОГУРЕЦ"
        ),

        SecondHolderData(
            id = "6",
            headerText = "Hello",
            imageUrl = "https://sun9-57.userapi.com/impf/gCmAs3zo5HK1Kzuv7LZMBnxLZdTRUYu9_0iXsQ/B5BMbn_4E2k.jpg?size=800x600&quality=96&sign=adb2ffac14992430abfb26e4aba50420&c_uniq_tag=85hnrQPfVQRuPTe4xRgRrFhwXmQA305wGX18wqWePIo&type=album",
            descText = "Красивый молодой человек появился)))))))))"
        ),
        SecondHolderData(
            id = "7",
            headerText = "Solo Valya",
            imageUrl = "https://avatars.mds.yandex.net/i?id=3e9c1675566c9e4684503659e9b3e73bc6bfd9fd-7763602-images-thumbs&n=13",
            descText = "Красавец Валька"
        ),
        SecondHolderData(
            id = "8",
            headerText = "Vitek",
            imageUrl = "https://sun9-75.userapi.com/c9564/u68602111/-14/x_7fae0c59.jpg",
            descText = "Kukushka uexala glaza upolzli"
        ),
        SecondHolderData(
            id = "9",
            headerText = "Anton",
            imageUrl = "https://cs14.pikabu.ru/post_img/2021/08/16/3/og_og_1629083152213670462.jpg",
            descText = "Goodbye friend"
        ),
        SecondHolderData(
            id = "10",
            headerText = "Sasha Sasha",
            imageUrl = "https://s.iimg.su/s/18/th_wSOBIrfrfKBn1lEsngZvJVJFots8YrnGcibfNOMR.jpg",
            descText = "Hahahahahahahhahahahah"
        ),
        SecondHolderData(
            id = "11",
            headerText = "Zurib",
            imageUrl = "https://cs8.pikabu.ru/post_img/2018/03/15/8/og_og_1521117231228324726.jpg",
            descText = "No description"
        ),
        SecondHolderData(
            id = "12",
            headerText = "Ahmed Al Asri",
            imageUrl = "https://sun9-79.userapi.com/c10402/u68484497/-14/x_cd59bdd4.jpg",
            descText = "Urma Halyad"
        ),
        SecondHolderData(
            id = "13",
            headerText = "Gambler Sayner",
            imageUrl = "https://cs13.pikabu.ru/post_img/2023/04/12/7/og_og_1681293904244985136.jpg",
            descText = "HAHAHAHAHAH TROLLED"
        ),
        SecondHolderData(
            id = "14",
            headerText = "Zhuravl Zita",
            imageUrl = "https://sun9-50.userapi.com/impf/impinZMUhsaNC5LdWpmEWnJWEA-qqqXck2VvVA/lxS-S3G33IY.jpg?size=604x377&quality=96&sign=e6370b55e18847c0e025c433c9e2740e&c_uniq_tag=ITiNZCL3O9O0JHUnrs42fMWFbkLQVT6ANccBOThqvWM&type=album",
            descText = "SIGMA MISS SIGMA"
        ),
        SecondHolderData(
            id = "15",
            headerText = "Merhamibadad",
            imageUrl = "https://sun9-71.userapi.com/impf/dYILxT8SwD0wg364H1kPR5mug8i3lIXqtX2FsQ/h9sqhlmJguk.jpg?size=604x378&quality=96&sign=f3a5bf35033cbfdd0681c7a59f2b6008&type=album",
            descText = "MALE AWESOME GOD"
        ),
        SecondHolderData(
            id = "16",
            headerText = "Zak",
            imageUrl = "https://avatars.mds.yandex.net/i?id=2af73ef24243b5c74d6019360b09b8a9_l-10843465-images-thumbs&n=13",
            descText = "pupupupupupupupup"
        ),
        SecondHolderData(
            id = "17",
            headerText = "Rylie",
            imageUrl = "https://yandex-images.clstorage.net/9C9vzk336/e338a5tBAw4/Urh_j8OtUbLiRB3nHZermjI518R4RAmhHQo2kWirrApfJ5VPSln4ANnFQsFqMKzFRdt3VLX2WTTOkDHi7YKZ6ITrKp8UPJGzp1bfdBsUr59hPocC_NIKrJPVKsxm9eBWQ_nInYsPLAAeQpKSZL58hg1cc5Dpbhg1Uos1qn_WDTBX-TvHQ3ibemJWXu3cE-OdqXEcD29D1yXX2aDFa3glFkK2xpZITiSFklrU06i17wYOVvZQZWASP9SB_uwzFc63X7l0CgH8l3nkXpbm0lQqFSExHkAuQ53vWJRoTqj24JqKv4HYwoiky1WSG9Ii8ycBSJPyH2KtWTuZjjapsxaEMZI7dYJFNxz7rJ8Wa9acqRRi-ZSDbYsTYN8UaU7sde-QjnYG1s8GZICYG1_RpfumwMPftZzsaBMx1In87_eWh38RsbFHSvZWN-ZUWu1XmiuWZLaaS-vEli8U22FNK7Pnnoj2DtfCBm1I0luWWqfyIY2GGbNTLaLQv5jHciw-moL8Gnfxzon13HenVdaiU9sjHqA_nE1pgBvpFRsohuF9rJCPOAnWQEjhzZMV1lYo_e8GD9e90Gsvm3WfyXzn8h1MP1A-eYrD-p8yJtcRZNZcJt2qtFvJIg_eJlCbbI-leiHfAHLCk41JZwseXVecp7tnBAMYcl4nrhN8EkY-4_LTQTDYvrpGwzKbPe2W3eZaUuxV7XEWz-PK12hQUOcI4DfhUcbzBxmARmzDlNhXG2g87Y0FUXtW7WNVudPHsqq6lQg-1Pb7x0L10zYl29qtGlsoHyN8F8oggtsuEV0rAutzLZWG9c1Zi4gvT1ZSGpIt-GrGAJl3V6pmHXQejX3pfh8OPlf4O4oNPhY-4hZZrdvUJ1Gh9xJA4ssdKBoY4YcntClZQ7qAkYoMacLZ1JaVr_eujsQSeF7h5t13EkC6aHUXSb_WMHPJhb6eOOOVH6-bWOJfoD5QiijGnu1YFuyEb4",
            descText = "Играй гармонь ты моя черная и белая и красная и желтая"
        ),
        SecondHolderData(
            id = "18",
            headerText = "Ramper Za",
            imageUrl = "https://avatars.mds.yandex.net/i?id=ec9dad3b89a7fe00f8a6c922106aa3a7_l-4119765-images-thumbs&n=13",
            descText = "No description"
        ),
        SecondHolderData(
            id = "19",
            headerText = "Rustik",
            imageUrl = "https://sun9-75.userapi.com/c9564/u68602111/-14/x_7fae0c59.jpg",
            descText = "No description"
        ),
        SecondHolderData(
            id = "20",
            headerText = "Ruslan Barno",
            imageUrl = "https://sun9-48.userapi.com/impf/c623122/v623122928/11ea4/EiH0VnaESfQ.jpg?size=807x454&quality=96&sign=7fd01cdb16e8fa90f18879d0dc53506f&c_uniq_tag=tiVCVD3Ml0-bM6dZ9Hzf3F89CmAoV_P0vt7RLjO1Myc&type=album",
            descText = "No description"
        ),
        SecondHolderData(
            id = "21",
            headerText = "Solomer",
            imageUrl = "https://yandex-images.clstorage.net/9C9vzk336/e338a5tBAw4/Urh_j8OtUbLiRB3nHZermjI518R4RAmhHQo2UqhqON1LMoNaXlgsFYyFg8P_J_gSxMz2gPf3mbSaUbHi7YOYaQdpad8T7ZCwptZa441TqNli-tJQ6BEKN5PSqsyhsecQgHpJUE0M7oBZFwWTrj5jVMdSMJCkaxS0mk1wKnVWzbyfeHuPS3rUum8TWmIUlK-VYDKdy-WCm-oalqZGqLxq1cB7Bl5Gz6-I3Jsd1e_yaMdHUToVZOcavpDLeuEzGg5_GHA7wIY4ULZslVKqH5roXOi63cmqw5ug0BnrjiN_5BBMsw8ZCEVpCJJc1RrueC4PQBf_ESvg1_WUhnUpdpoNNZ69ucEOfJs97t2TKpKeIN_p-RVDaYBTLl-ea48jO2cfRrfGngZI58jZkVxe7f8mjoGcs1aobhK_nsty4zDejTbQPjMFy3NadyKXWGYUHyZda_9aTaEGHKsQVaSHIPyhF0ixRpYHRefBEdscl2b9LwnM2XVQZ6_YfBCHPG3zF4EzUzX1RUKwWf1i1lnqWxtnVeM700unhFgm1lRkzus2b1xGtoiRBsepTZSaUtdqciDHR1C7XuTpVTfagDJjONsG_ZB_vM0G-la-J1fbLZ2e4FAgMViMI8_X4xkR5IricmjRCTnCWkMKqMtSFxpWoP1mT0VcdJFqpdS_0w29ITzbyPCf9zwOyvqeuiicmuEfEu6aqHadwSUOEupXES8BZTclEYf_Q5_JB6fAmp2fVicy4cXMWj2YYm8Z-RbMPK11k465kft0T0t7WfIjVNfmVFLnXuK4kMAgzVrmEVAizW88Yd1Dv0VViQxpgxZfFRuudyVCh1_1lGMoHLHbAXZiNlcEv5F4eo8GNJI3K5MaZVSTaFGsOhvFqg8TIBdbZwfq8KbZj3oKGEEN7cWb0JOXqfUqhs-bfptqY5xx2A27pbdcDPgQ-bLHRbwSvy2SmSNW0-SUojvSh2DFHqPSGWkK6Y",
            descText = "No description"
        ),
        SecondHolderData(
            id = "22",
            headerText = "Zhirovka Yodova",
            imageUrl = "https://yandex-images.clstorage.net/9C9vzk336/e338a5tBAw4/Urh_j8OtUbLiRB3nHZermjI518R4RAmhHQoiBCl_esgLs8JN3swsAE4HV4O_5PkR0Bp3QeFimPRaUzHi7YOYa4SoaZ4RLZCwptZa441TqNli-tJQ6BEKN5PSqsyhsecQgHpJUE0M7oBZFwWTrj5jVMdSMJCkaxS0mk1wKnVWzbyfeHuPS3rUum8TWmIUlK-VYDKdy-WCm-oalqZGqLxq1cB7Bl5Gz6-I3Jsd1e_yaMdHUToVZOcavpDLeuEzGg5_GHA7wIY4ULZslVKqH5roXOi63cmqw5ug0BnrjiN_5BBMsw8ZCEVpCJJc1RrueC4PQBf_ESvg1_WUhnUpdpoNNZ69ucEOfJs97t2TKpKeIN_p-RVDaYBTLl-ea48jO2cfRrfGngZI58jZkVxe7f8mjoGcs1aobhK_nsty4zDejTbQPjMFy3NadyKXWGYUHyZda_9aTaEGHKsQVaSHIPyhF0ixRpYHRefBEdscl2b9LwnM2XVQZ6_YfBCHPG3zF4EzUzX1RUKwWf1i1lnqWxtnVeM700unhFgm1lRkzus2b1xGtoiRBsepTZSaUtdqciDHR1C7XuTpVTfagDJjONsG_ZB_vM0G-la-J1fbLZ2e4FAgMViMI8_X4xkR5IricmjRCTnCWkMKqMtSFxpWoP1mT0VcdJFqpdS_0w29ITzbyPCf9zwOyvqeuiicmuEfEu6aqHadwSUOEupXES8BZTclEYf_Q5_JB6fAmp2fVicy4cXMWj2YYm8Z-RbMPK11k465kft0T0t7WfIjVNfmVFLnXuK4kMAgzVrmEVAizW88Yd1Dv0VViQxpgxZfFRuudyVCh1_1lGMoHLHbAXZiNlcEv5F4eo8GNJI3K5MaZVSTaFGsOhvFqg8TIBdbZwfq8KbZj3oKGEEN7cWb0JOXqfUqhs-bfptqY5xx2A27pbdcDPgQ-bLHRbwSvy2SmSNW0-SUojvSh2DFHqPSGWkK6Y",
            descText = "Любитель мультиков для подростков"
        ),
        SecondHolderData(
            id = "23",
            headerText = "Zaza",
            imageUrl = "https://sun9-1.userapi.com/c9547/u16076921/-14/y_1243fa23.jpg",
            descText = "ВСЕ В БЛЮСКАЙ ТВИТТЕР СДУЛСЯ!!!!"
        ),
        SecondHolderData(
            id = "24",
            headerText = "Heheheh",
            imageUrl = "https://sun9-67.userapi.com/impf/9aulnubFxAJDH-MygpXPMxoq9G96R2NDKFqiiQ/X30ybdLZVe4.jpg?size=604x340&quality=96&sign=d2e0c604867bb565c3643ff765b70fa3&c_uniq_tag=26h7jE4Ott0mLsA3v06MKfgsVRewDao-m4_JLaFU6Vs&type=album",
            descText = "НУ И ЧТО ТУТ ПИСАТЬ7"
        ),
        SecondHolderData(
            id = "25",
            headerText = "Mister M",
            imageUrl = "https://avatars.mds.yandex.net/i?id=18ebdb63e7184d40b4445ff07d800f2e_l-8356382-images-thumbs&n=13",
            descText = "КАК ЭТО СЛУЧИЛОСЬ"
        ),
        SecondHolderData(
            id = "26",
            headerText = "Stan Smith",
            imageUrl = "https://i.pinimg.com/736x/6f/b9/e9/6fb9e9aa54b747648b55eaa2edffe8a8.jpg",
            descText = "No description"
        ),
        SecondHolderData(
            id = "27",
            headerText = "Zuram Ahmed",
            imageUrl = "https://lastfm.freetls.fastly.net/i/u/ar0/84060236169b4854c8c7734f1bc5ff98.png",
            descText = "ЗАЧЕМ Я ЭТО ВСЕ ПРОПИСАЛ МОГ ЖЕ ПРОСТО РАНДОМОМ ВЫБРАТЬ ПОТОМ АЙ ЛАДНО НОРМ ТЕМА"
        ),
    )
}