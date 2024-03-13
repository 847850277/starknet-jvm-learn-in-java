package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;


@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\b\u0016\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0002\u0010\u0006B\u0015\b\u0016\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\u0010\tB\u0007\b\u0016¢\u0006\u0002\u0010\nB\u0013\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\u0010\fJ\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0001J\u0019\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0001J\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0096\u0001J\u0017\u0010\u0016\u001a\u00020\u00122\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0096\u0001J\t\u0010\u0017\u001a\u00020\u0014H\u0096\u0001J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÂ\u0003J\u0011\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0003J\u0017\u0010\u001a\u001a\u00020\u00122\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0096\u0001J\u0019\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\u0011\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000eH\u0096\u0003J\t\u0010 \u001a\u00020\u000eHÖ\u0001J\u0011\u0010!\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0001J\t\u0010\"\u001a\u00020\u0012H\u0096\u0001J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030$H\u0096\u0003J\u0011\u0010%\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0001J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'H\u0096\u0001J\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'2\u0006\u0010\u0015\u001a\u00020\u000eH\u0096\u0001J\u0011\u0010(\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0001J\u0017\u0010)\u001a\u00020\u00122\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0096\u0001J\u0011\u0010*\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000eH\u0096\u0001J\u0017\u0010+\u001a\u00020\u00122\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0096\u0001J\u0019\u0010,\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003H\u0096\u0003J\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020\u000eH\u0096\u0001J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u000301H\u0016J\t\u00102\u001a\u000203HÖ\u0001R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\u000eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u00064"},
        d2 = {"Lcom/swmansion/starknet/data/types/FeltArray;", "Lcom/swmansion/starknet/data/types/conversions/ConvertibleToCalldata;", "", "Lcom/swmansion/starknet/data/types/Felt;", "elements", "", "([Lcom/swmansion/starknet/data/types/Felt;)V", "collection", "", "(Ljava/util/Collection;)V", "()V", "list", "(Ljava/util/List;)V", "size", "", "getSize", "()I", "add", "", "element", "", "index", "addAll", "clear", "component1", "contains", "containsAll", "copy", "equals", "other", "", "get", "hashCode", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "retainAll", "set", "subList", "fromIndex", "toIndex", "toCalldata", "", "toString", "", "starknet-jvm-learn"}
)
public class FeltArray implements ConvertibleToCalldata, List, KMutableList {

    private final List list;

    @NotNull
    public List toCalldata() {
        return CollectionsKt.toList((Iterable)this.list);
    }

    public FeltArray(@NotNull List list) {
        super();
        //Intrinsics.checkNotNullParameter(list, "list");
        this.list = new ArrayList(list);
    }

    public FeltArray(@NotNull Felt... elements) {
        this(ArraysKt.toMutableList(elements));
    }

    public FeltArray(@NotNull Collection collection) {
        this(CollectionsKt.toMutableList(collection));
    }

    public FeltArray() {
        this((Collection)CollectionsKt.emptyList());
    }

    public int getSize() {
        return this.list.size();
    }

    // $FF: bridge method
    public final int size() {
        return this.getSize();
    }


    public boolean add(@NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        return this.list.add(element);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public boolean add(Object var1) {
        return this.add((Felt)var1);
    }

    public void add(int index, @NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        this.list.add(index, element);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public void add(int var1, Object var2) {
        this.add(var1, (Felt)var2);
    }

    public boolean addAll(int index, @NotNull Collection elements) {
        //Intrinsics.checkNotNullParameter(elements, "elements");
        return this.list.addAll(index, elements);
    }

    public boolean addAll(@NotNull Collection elements) {
        //Intrinsics.checkNotNullParameter(elements, "elements");
        return this.list.addAll(elements);
    }

    public void clear() {
        this.list.clear();
    }

    public boolean contains(@NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        return this.list.contains(element);
    }

    // $FF: bridge method
    public final boolean contains(Object var1) {
        return !(var1 instanceof Felt) ? false : this.contains((Felt)var1);
    }

    public boolean containsAll(@NotNull Collection elements) {
        //Intrinsics.checkNotNullParameter(elements, "elements");
        return this.list.containsAll(elements);
    }

//    @NotNull
//    public Felt get(int index) {
//        Object var10000 = this.list.get(index);
//        Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
//        return (Felt)var10000;
//    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object get(int var1) {
        return this.get(var1);
    }

    public int indexOf(@NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        return this.list.indexOf(element);
    }

    // $FF: bridge method
    public final int indexOf(Object var1) {
        return !(var1 instanceof Felt) ? -1 : this.indexOf((Felt)var1);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @NotNull
    public Iterator iterator() {
        return this.list.iterator();
    }

    public int lastIndexOf(@NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        return this.list.lastIndexOf(element);
    }

    // $FF: bridge method
    public final int lastIndexOf(Object var1) {
        return !(var1 instanceof Felt) ? -1 : this.lastIndexOf((Felt)var1);
    }

    @NotNull
    public ListIterator listIterator() {
        return this.list.listIterator();
    }

    @NotNull
    public ListIterator listIterator(int index) {
        return this.list.listIterator(index);
    }

    public boolean remove(@NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        return this.list.remove(element);
    }

    // $FF: bridge method
    public final boolean remove(Object var1) {
        return !(var1 instanceof Felt) ? false : this.remove((Felt)var1);
    }

    public boolean removeAll(@NotNull Collection elements) {
        //Intrinsics.checkNotNullParameter(elements, "elements");
        return this.list.removeAll(elements);
    }

    @NotNull
    public Felt removeAt(int index) {
        Object var10000 = this.list.remove(index);
        //Intrinsics.checkNotNullExpressionValue(var10000, "removeAt(...)");
        return (Felt)var10000;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object remove(int var1) {
        return this.removeAt(var1);
    }

    // $FF: bridge method
//    public final Felt remove(int var1) {
//        return this.removeAt(var1);
//    }

    public boolean retainAll(@NotNull Collection elements) {
        //Intrinsics.checkNotNullParameter(elements, "elements");
        return this.list.retainAll(elements);
    }

    @NotNull
    public Felt set(int index, @NotNull Felt element) {
        //Intrinsics.checkNotNullParameter(element, "element");
        Object var10000 = this.list.set(index, element);
        //Intrinsics.checkNotNullExpressionValue(var10000, "set(...)");
        return (Felt)var10000;
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object set(int var1, Object var2) {
        return this.set(var1, (Felt)var2);
    }

    @NotNull
    public List subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }

    private final List component1() {
        return this.list;
    }

    @NotNull
    public final FeltArray copy(@NotNull List list) {
        //Intrinsics.checkNotNullParameter(list, "list");
        return new FeltArray(list);
    }

    // $FF: synthetic method
    public static FeltArray copy$default(FeltArray var0, List var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = var0.list;
        }

        return var0.copy(var1);
    }

    @NotNull
    public String toString() {
        return "FeltArray(list=" + this.list + ")";
    }

    public int hashCode() {
        List var10000 = this.list;
        return var10000 != null ? var10000.hashCode() : 0;
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof FeltArray) {
                FeltArray var2 = (FeltArray)var1;
                if (Intrinsics.areEqual(this.list, var2.list)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public Object[] toArray(Object[] var1) {
        return CollectionToArray.toArray(this, var1);
    }

}
