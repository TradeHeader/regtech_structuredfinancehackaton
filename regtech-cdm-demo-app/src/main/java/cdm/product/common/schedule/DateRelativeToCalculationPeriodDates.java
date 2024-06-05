package cdm.product.common.schedule;

import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilderImpl;
import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesImpl;
import cdm.product.common.schedule.meta.DateRelativeToCalculationPeriodDatesMeta;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder;
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
@RosettaDataType(value="DateRelativeToCalculationPeriodDates", builder=DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilderImpl.class, version="${project.version}")
public interface DateRelativeToCalculationPeriodDates extends RosettaModelObject {

	DateRelativeToCalculationPeriodDatesMeta metaData = new DateRelativeToCalculationPeriodDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A set of href pointers to calculation period dates defined somewhere else in the document.
	 */
	List<? extends ReferenceWithMetaCalculationPeriodDates> getCalculationPeriodDatesReference();

	/*********************** Build Methods  ***********************/
	DateRelativeToCalculationPeriodDates build();
	
	DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder toBuilder();
	
	static DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder builder() {
		return new DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DateRelativeToCalculationPeriodDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DateRelativeToCalculationPeriodDates> getType() {
		return DateRelativeToCalculationPeriodDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.class, getCalculationPeriodDatesReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DateRelativeToCalculationPeriodDatesBuilder extends DateRelativeToCalculationPeriodDates, RosettaModelObjectBuilder {
		ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference(int _index);
		List<? extends ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder> getCalculationPeriodDatesReference();
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference0);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference1, int _idx);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference2);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference3, int _idx);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(List<? extends ReferenceWithMetaCalculationPeriodDates> calculationPeriodDatesReference4);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder setCalculationPeriodDatesReference(List<? extends ReferenceWithMetaCalculationPeriodDates> calculationPeriodDatesReference5);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(List<? extends CalculationPeriodDates> calculationPeriodDatesReference6);
		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder setCalculationPeriodDatesReferenceValue(List<? extends CalculationPeriodDates> calculationPeriodDatesReference7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder.class, getCalculationPeriodDatesReference());
		}
		

		DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder prune();
	}

	/*********************** Immutable Implementation of DateRelativeToCalculationPeriodDates  ***********************/
	class DateRelativeToCalculationPeriodDatesImpl implements DateRelativeToCalculationPeriodDates {
		private final List<? extends ReferenceWithMetaCalculationPeriodDates> calculationPeriodDatesReference;
		
		protected DateRelativeToCalculationPeriodDatesImpl(DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder builder) {
			this.calculationPeriodDatesReference = ofNullable(builder.getCalculationPeriodDatesReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public List<? extends ReferenceWithMetaCalculationPeriodDates> getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates build() {
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder toBuilder() {
			DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder builder) {
			ofNullable(getCalculationPeriodDatesReference()).ifPresent(builder::setCalculationPeriodDatesReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToCalculationPeriodDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToCalculationPeriodDates {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference +
			'}';
		}
	}

	/*********************** Builder Implementation of DateRelativeToCalculationPeriodDates  ***********************/
	class DateRelativeToCalculationPeriodDatesBuilderImpl implements DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder {
	
		protected List<ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder> calculationPeriodDatesReference = new ArrayList<>();
	
		public DateRelativeToCalculationPeriodDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public List<? extends ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder> getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		public ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference(int _index) {
		
			if (calculationPeriodDatesReference==null) {
				this.calculationPeriodDatesReference = new ArrayList<>();
			}
			ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder result;
			return getIndex(calculationPeriodDatesReference, _index, () -> {
						ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder newCalculationPeriodDatesReference = ReferenceWithMetaCalculationPeriodDates.builder();
						return newCalculationPeriodDatesReference;
					});
		}
		
	
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference) {
			if (calculationPeriodDatesReference!=null) this.calculationPeriodDatesReference.add(calculationPeriodDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference, int _idx) {
			getIndex(this.calculationPeriodDatesReference, _idx, () -> calculationPeriodDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference) {
			this.getOrCreateCalculationPeriodDatesReference(-1).setValue(calculationPeriodDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference, int _idx) {
			this.getOrCreateCalculationPeriodDatesReference(_idx).setValue(calculationPeriodDatesReference.toBuilder());
			return this;
		}
		@Override 
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReference(List<? extends ReferenceWithMetaCalculationPeriodDates> calculationPeriodDatesReferences) {
			if (calculationPeriodDatesReferences != null) {
				for (ReferenceWithMetaCalculationPeriodDates toAdd : calculationPeriodDatesReferences) {
					this.calculationPeriodDatesReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("calculationPeriodDatesReference")
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder setCalculationPeriodDatesReference(List<? extends ReferenceWithMetaCalculationPeriodDates> calculationPeriodDatesReferences) {
			if (calculationPeriodDatesReferences == null)  {
				this.calculationPeriodDatesReference = new ArrayList<>();
			}
			else {
				this.calculationPeriodDatesReference = calculationPeriodDatesReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder addCalculationPeriodDatesReferenceValue(List<? extends CalculationPeriodDates> calculationPeriodDatesReferences) {
			if (calculationPeriodDatesReferences != null) {
				for (CalculationPeriodDates toAdd : calculationPeriodDatesReferences) {
					this.addCalculationPeriodDatesReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder setCalculationPeriodDatesReferenceValue(List<? extends CalculationPeriodDates> calculationPeriodDatesReferences) {
			this.calculationPeriodDatesReference.clear();
			if (calculationPeriodDatesReferences!=null) {
				calculationPeriodDatesReferences.forEach(this::addCalculationPeriodDatesReferenceValue);
			}
			return this;
		}
		
		
		@Override
		public DateRelativeToCalculationPeriodDates build() {
			return new DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesImpl(this);
		}
		
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder prune() {
			calculationPeriodDatesReference = calculationPeriodDatesReference.stream().filter(b->b!=null).<ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationPeriodDatesReference()!=null && getCalculationPeriodDatesReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder o = (DateRelativeToCalculationPeriodDates.DateRelativeToCalculationPeriodDatesBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriodDatesReference(), o.getCalculationPeriodDatesReference(), this::getOrCreateCalculationPeriodDatesReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToCalculationPeriodDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToCalculationPeriodDatesBuilder {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference +
			'}';
		}
	}
}
