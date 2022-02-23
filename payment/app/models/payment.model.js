module.exports = mongoose => {
  const Payment = mongoose.model(
    "payment",
    mongoose.Schema(
      {
        name: String,
        description: String,
        card: String,
        price: String
      },
      { timestamps: true }
    )
  );
  return Tutorial;
};