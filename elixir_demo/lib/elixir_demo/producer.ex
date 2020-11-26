defmodule ElixirDemo.Producer do
  @moduledoc false

  use Task

  require Logger

  def start_link(_) do
    Task.start_link(__MODULE__, :run, [])
  end

  # Implementation

  def run do
    messages =
      for i <- 1..100 do
        key = to_string(i)
        value = "message #" <> to_string(i)
        {key, value}
      end

    Kaffe.Producer.produce("cities", messages)
    :ok
  end
end
