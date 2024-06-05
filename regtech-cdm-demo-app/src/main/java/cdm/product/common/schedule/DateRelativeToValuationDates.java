package cdm.product.common.schedule;

import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.metafields.ReferenceWithMetaPerformanceValuationDates;
import cdm.observable.asset.metafields.ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder;
import cdm.product.common.schedule.DateRelativeToValuationDates;
import cdm.product.common.schedule.DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder;
import cdm.product.common.schedule.DateRelativeToValuationDates.DateRelativeToValuationDatesBuilderImpl;
import cdm.product.common.schedule.DateRelativeToValuationDates.DateRelativeToValuationDatesImpl;
import cdm.product.common.schedule.meta.DateRelativeToValuationDatesMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data to:  provide the ability to point to multiple payment nodes in the document through the unbounded paymentDatesReference.
 * @version ${project.version}
 */
@RosettaDataType(value="DateRelativeToValuationDates", builder=DateRelativeToValuationDates.DateRelativeToValuationDatesBuilderImpl.class, version="${project.version}")
public interface DateRelativeToValuationDates extends RosettaModelObject {

	DateRelativeToValuationDatesMeta metaData = new DateRelativeToValuationDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A set of href pointers to valuation period dates defined somewhere else in the document.
	 */
	List<? extends ReferenceWithMetaPerformanceValuationDates> getValuationDatesReference();

	/*********************** Build Methods  ***********************/
	DateRelativeToValuationDates build();
	
	DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder toBuilder();
	
	static DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder builder() {
		return new DateRelativeToValuationDates.DateRelativeToValuationDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DateRelativeToValuationDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DateRelativeToValuationDates> getType() {
		return DateRelativeToValuationDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationDatesReference"), processor, ReferenceWithMetaPerformanceValuationDates.class, getValuationDatesReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DateRelativeToValuationDatesBuilder extends DateRelativeToValuationDates, RosettaModelObjectBuilder {
		ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder getOrCreateValuationDatesReference(int _index);
		List<? extends ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder> getValuationDatesReference();
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(ReferenceWithMetaPerformanceValuationDates valuationDatesReference0);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(ReferenceWithMetaPerformanceValuationDates valuationDatesReference1, int _idx);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(PerformanceValuationDates valuationDatesReference2);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(PerformanceValuationDates valuationDatesReference3, int _idx);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(List<? extends ReferenceWithMetaPerformanceValuationDates> valuationDatesReference4);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder setValuationDatesReference(List<? extends ReferenceWithMetaPerformanceValuationDates> valuationDatesReference5);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(List<? extends PerformanceValuationDates> valuationDatesReference6);
		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder setValuationDatesReferenceValue(List<? extends PerformanceValuationDates> valuationDatesReference7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationDatesReference"), processor, ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder.class, getValuationDatesReference());
		}
		

		DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder prune();
	}

	/*********************** Immutable Implementation of DateRelativeToValuationDates  ***********************/
	class DateRelativeToValuationDatesImpl implements DateRelativeToValuationDates {
		private final List<? extends ReferenceWithMetaPerformanceValuationDates> valuationDatesReference;
		
		protected DateRelativeToValuationDatesImpl(DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder builder) {
			this.valuationDatesReference = ofNullable(builder.getValuationDatesReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("valuationDatesReference")
		public List<? extends ReferenceWithMetaPerformanceValuationDates> getValuationDatesReference() {
			return valuationDatesReference;
		}
		
		@Override
		public DateRelativeToValuationDates build() {
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder toBuilder() {
			DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder builder) {
			ofNullable(getValuationDatesReference()).ifPresent(builder::setValuationDatesReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToValuationDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(valuationDatesReference, _that.getValuationDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationDatesReference != null ? valuationDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToValuationDates {" +
				"valuationDatesReference=" + this.valuationDatesReference +
			'}';
		}
	}

	/*********************** Builder Implementation of DateRelativeToValuationDates  ***********************/
	class DateRelativeToValuationDatesBuilderImpl implements DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder {
	
		protected List<ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder> valuationDatesReference = new ArrayList<>();
	
		public DateRelativeToValuationDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("valuationDatesReference")
		public List<? extends ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder> getValuationDatesReference() {
			return valuationDatesReference;
		}
		
		public ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder getOrCreateValuationDatesReference(int _index) {
		
			if (valuationDatesReference==null) {
				this.valuationDatesReference = new ArrayList<>();
			}
			ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder result;
			return getIndex(valuationDatesReference, _index, () -> {
						ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder newValuationDatesReference = ReferenceWithMetaPerformanceValuationDates.builder();
						return newValuationDatesReference;
					});
		}
		
	
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(ReferenceWithMetaPerformanceValuationDates valuationDatesReference) {
			if (valuationDatesReference!=null) this.valuationDatesReference.add(valuationDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(ReferenceWithMetaPerformanceValuationDates valuationDatesReference, int _idx) {
			getIndex(this.valuationDatesReference, _idx, () -> valuationDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(PerformanceValuationDates valuationDatesReference) {
			this.getOrCreateValuationDatesReference(-1).setValue(valuationDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(PerformanceValuationDates valuationDatesReference, int _idx) {
			this.getOrCreateValuationDatesReference(_idx).setValue(valuationDatesReference.toBuilder());
			return this;
		}
		@Override 
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReference(List<? extends ReferenceWithMetaPerformanceValuationDates> valuationDatesReferences) {
			if (valuationDatesReferences != null) {
				for (ReferenceWithMetaPerformanceValuationDates toAdd : valuationDatesReferences) {
					this.valuationDatesReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("valuationDatesReference")
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder setValuationDatesReference(List<? extends ReferenceWithMetaPerformanceValuationDates> valuationDatesReferences) {
			if (valuationDatesReferences == null)  {
				this.valuationDatesReference = new ArrayList<>();
			}
			else {
				this.valuationDatesReference = valuationDatesReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder addValuationDatesReferenceValue(List<? extends PerformanceValuationDates> valuationDatesReferences) {
			if (valuationDatesReferences != null) {
				for (PerformanceValuationDates toAdd : valuationDatesReferences) {
					this.addValuationDatesReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder setValuationDatesReferenceValue(List<? extends PerformanceValuationDates> valuationDatesReferences) {
			this.valuationDatesReference.clear();
			if (valuationDatesReferences!=null) {
				valuationDatesReferences.forEach(this::addValuationDatesReferenceValue);
			}
			return this;
		}
		
		
		@Override
		public DateRelativeToValuationDates build() {
			return new DateRelativeToValuationDates.DateRelativeToValuationDatesImpl(this);
		}
		
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder prune() {
			valuationDatesReference = valuationDatesReference.stream().filter(b->b!=null).<ReferenceWithMetaPerformanceValuationDates.ReferenceWithMetaPerformanceValuationDatesBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationDatesReference()!=null && getValuationDatesReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder o = (DateRelativeToValuationDates.DateRelativeToValuationDatesBuilder) other;
			
			merger.mergeRosetta(getValuationDatesReference(), o.getValuationDatesReference(), this::getOrCreateValuationDatesReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToValuationDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(valuationDatesReference, _that.getValuationDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationDatesReference != null ? valuationDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToValuationDatesBuilder {" +
				"valuationDatesReference=" + this.valuationDatesReference +
			'}';
		}
	}
}
