module.exports = mongoose => {
  var schema = mongoose.Schema(
    {
      name: String,
      description: String,
      card: String,
      price: String
    },
    { timestamps: true }
  );

  schema.method("toJSON", function() {
    const { __v, _id, ...object } = this.toObject();
    object.id = _id;
    return object;
  });

  const Payment = mongoose.model("payment", schema);
  return Payment;
};
