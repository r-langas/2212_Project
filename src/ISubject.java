public interface ISubject {
    void Subscribe(IObserver observer);
    void Notify(Property property);
}
