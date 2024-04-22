//Cairo 2.1.0

#[starknet::interface]
pub trait ITestContract<TContractState> {
    fn increase_counter(ref self: TContractState, amount: u128);

    // 入参为u16类型的val1,出参为MyEnum
    fn test2(self: @TContractState, val1: u16) -> MyEnum;

}


//simple struct
#[derive(Copy, Drop, Serde)]
struct Order {
    p1: felt252,
    p2: u16,
}

#[derive(Drop, Serde, Append)]
enum MyEnum {
    Response: Order,
    Warning: felt252,
    Error: (u16,u16),
    Critical: Array<u32>,
    Empty:()
}

// struct with enum
#[derive(Drop, Serde)]
struct OrderW {
    p1: felt252,
    my_enum: MyEnum,
    adds: Option<u8>,
}

#[starknet::contract]
mod MyTestContract {

    use starknet::ContractAddress;
    use starknet::contract_address_const;

    use super::{Order, MyEnum, OrderW};

    #[storage]
    struct Storage {
        counter: u128,
    }

    #[event]
    #[derive(Drop, starknet::Event)]
    enum Event {
        CounterIncreased: CounterIncreased,
        CounterDecreased: CounterDecreased
    }

    #[derive(Drop, starknet::Event)]
    struct CounterIncreased {
        #[key]
        from: ContractAddress,
        amount: u128
    }

    #[derive(Drop, starknet::Event)]
    struct CounterDecreased {
        amount: u128
    }

    // 这个是干嘛的？
    #[l1_handler]
    fn increase_bal(ref self: ContractState, from_address: felt252, amount: u128) {
        let current = self.counter.read();
        self.counter.write(current + amount);
    }


    #[constructor]
    fn constructor(ref self: ContractState, intial_value: u128) {
        self.counter.write(intial_value);
    }

    #[abi(embed_v0)]
    impl CounterContract of super::ITestContract<ContractState> {

        fn increase_counter(ref self: ContractState, amount: u128) {
            let current = self.counter.read();
            self.counter.write(current + amount);
            self
                .emit(
                    CounterIncreased {
                        from: contract_address_const::<0x6c8fe36d7454901424063b5c1b949d7e347ce3872d2487b30b76f3a4fd7c219>(),
                        amount: amount
                    }
                );
        }

        fn test2(self: @ContractState,val1: u16) ->MyEnum{
             if val1 < 100 {
                return MyEnum::Error((3,4));
            }
            if val1 == 100 {
                return MyEnum::Warning('attention:100');
            }
            if val1 < 150 {
                return MyEnum::Warning('attention:111');
            }
            if val1<200 {
                return MyEnum::Empty(());
            }
            MyEnum::Response(Order { p1: 1, p2: val1 })

        }

    }
}
