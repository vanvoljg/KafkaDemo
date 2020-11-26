defmodule ElixirDemo.Consumer do
  @moduledoc false

  #   %{
  #     headers: [],
  #     key: "47",
  #     offset: 43,
  #     partition: 1,
  #     topic: "cities",
  #     ts: 1606358168801,
  #     ts_type: :create,
  #     value: "message #47"
  #   }
  def handle_messages(messages) do
    for %{
          partition: partition,
          offset: offset,
          value: value
        } <- messages do
      IO.puts("partition: #{partition}, offset: #{offset}, value: #{value}")
    end

    :ok
  end
end
