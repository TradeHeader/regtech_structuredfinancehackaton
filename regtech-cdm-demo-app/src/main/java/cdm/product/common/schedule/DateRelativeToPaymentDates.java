package cdm.product.common.schedule;

import cdm.product.common.schedule.DateRelativeToPaymentDates;
import cdm.product.common.schedule.DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder;
import cdm.product.common.schedule.DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilderImpl;
import cdm.product.common.schedule.DateRelativeToPaymentDates.DateRelativeToPaymentDatesImpl;
import cdm.product.common.schedule.PaymentDates;
import cdm.product.common.schedule.meta.DateRelativeToPaymentDatesMeta;
import cdm.product.common.schedule.metafields.ReferenceWithMetaPaymentDates;
import cdm.product.common.schedule.metafields.ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder;
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
@RosettaDataType(value="DateRelativeToPaymentDates", builder=DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilderImpl.class, version="${project.version}")
public interface DateRelativeToPaymentDates extends RosettaModelObject {

	DateRelativeToPaymentDatesMeta metaData = new DateRelativeToPaymentDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A set of href pointers to payment dates defined somewhere else in the document.
	 */
	List<? extends ReferenceWithMetaPaymentDates> getPaymentDatesReference();

	/*********************** Build Methods  ***********************/
	DateRelativeToPaymentDates build();
	
	DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder toBuilder();
	
	static DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder builder() {
		return new DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DateRelativeToPaymentDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DateRelativeToPaymentDates> getType() {
		return DateRelativeToPaymentDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("paymentDatesReference"), processor, ReferenceWithMetaPaymentDates.class, getPaymentDatesReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DateRelativeToPaymentDatesBuilder extends DateRelativeToPaymentDates, RosettaModelObjectBuilder {
		ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder getOrCreatePaymentDatesReference(int _index);
		List<? extends ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder> getPaymentDatesReference();
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(ReferenceWithMetaPaymentDates paymentDatesReference0);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(ReferenceWithMetaPaymentDates paymentDatesReference1, int _idx);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(PaymentDates paymentDatesReference2);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(PaymentDates paymentDatesReference3, int _idx);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(List<? extends ReferenceWithMetaPaymentDates> paymentDatesReference4);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder setPaymentDatesReference(List<? extends ReferenceWithMetaPaymentDates> paymentDatesReference5);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(List<? extends PaymentDates> paymentDatesReference6);
		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder setPaymentDatesReferenceValue(List<? extends PaymentDates> paymentDatesReference7);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("paymentDatesReference"), processor, ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder.class, getPaymentDatesReference());
		}
		

		DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder prune();
	}

	/*********************** Immutable Implementation of DateRelativeToPaymentDates  ***********************/
	class DateRelativeToPaymentDatesImpl implements DateRelativeToPaymentDates {
		private final List<? extends ReferenceWithMetaPaymentDates> paymentDatesReference;
		
		protected DateRelativeToPaymentDatesImpl(DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder builder) {
			this.paymentDatesReference = ofNullable(builder.getPaymentDatesReference()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("paymentDatesReference")
		public List<? extends ReferenceWithMetaPaymentDates> getPaymentDatesReference() {
			return paymentDatesReference;
		}
		
		@Override
		public DateRelativeToPaymentDates build() {
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder toBuilder() {
			DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder builder) {
			ofNullable(getPaymentDatesReference()).ifPresent(builder::setPaymentDatesReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToPaymentDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(paymentDatesReference, _that.getPaymentDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentDatesReference != null ? paymentDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToPaymentDates {" +
				"paymentDatesReference=" + this.paymentDatesReference +
			'}';
		}
	}

	/*********************** Builder Implementation of DateRelativeToPaymentDates  ***********************/
	class DateRelativeToPaymentDatesBuilderImpl implements DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder {
	
		protected List<ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder> paymentDatesReference = new ArrayList<>();
	
		public DateRelativeToPaymentDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("paymentDatesReference")
		public List<? extends ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder> getPaymentDatesReference() {
			return paymentDatesReference;
		}
		
		public ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder getOrCreatePaymentDatesReference(int _index) {
		
			if (paymentDatesReference==null) {
				this.paymentDatesReference = new ArrayList<>();
			}
			ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder result;
			return getIndex(paymentDatesReference, _index, () -> {
						ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder newPaymentDatesReference = ReferenceWithMetaPaymentDates.builder();
						return newPaymentDatesReference;
					});
		}
		
	
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(ReferenceWithMetaPaymentDates paymentDatesReference) {
			if (paymentDatesReference!=null) this.paymentDatesReference.add(paymentDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(ReferenceWithMetaPaymentDates paymentDatesReference, int _idx) {
			getIndex(this.paymentDatesReference, _idx, () -> paymentDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(PaymentDates paymentDatesReference) {
			this.getOrCreatePaymentDatesReference(-1).setValue(paymentDatesReference.toBuilder());
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(PaymentDates paymentDatesReference, int _idx) {
			this.getOrCreatePaymentDatesReference(_idx).setValue(paymentDatesReference.toBuilder());
			return this;
		}
		@Override 
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReference(List<? extends ReferenceWithMetaPaymentDates> paymentDatesReferences) {
			if (paymentDatesReferences != null) {
				for (ReferenceWithMetaPaymentDates toAdd : paymentDatesReferences) {
					this.paymentDatesReference.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("paymentDatesReference")
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder setPaymentDatesReference(List<? extends ReferenceWithMetaPaymentDates> paymentDatesReferences) {
			if (paymentDatesReferences == null)  {
				this.paymentDatesReference = new ArrayList<>();
			}
			else {
				this.paymentDatesReference = paymentDatesReferences.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder addPaymentDatesReferenceValue(List<? extends PaymentDates> paymentDatesReferences) {
			if (paymentDatesReferences != null) {
				for (PaymentDates toAdd : paymentDatesReferences) {
					this.addPaymentDatesReferenceValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder setPaymentDatesReferenceValue(List<? extends PaymentDates> paymentDatesReferences) {
			this.paymentDatesReference.clear();
			if (paymentDatesReferences!=null) {
				paymentDatesReferences.forEach(this::addPaymentDatesReferenceValue);
			}
			return this;
		}
		
		
		@Override
		public DateRelativeToPaymentDates build() {
			return new DateRelativeToPaymentDates.DateRelativeToPaymentDatesImpl(this);
		}
		
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder prune() {
			paymentDatesReference = paymentDatesReference.stream().filter(b->b!=null).<ReferenceWithMetaPaymentDates.ReferenceWithMetaPaymentDatesBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPaymentDatesReference()!=null && getPaymentDatesReference().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder o = (DateRelativeToPaymentDates.DateRelativeToPaymentDatesBuilder) other;
			
			merger.mergeRosetta(getPaymentDatesReference(), o.getPaymentDatesReference(), this::getOrCreatePaymentDatesReference);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DateRelativeToPaymentDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(paymentDatesReference, _that.getPaymentDatesReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (paymentDatesReference != null ? paymentDatesReference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DateRelativeToPaymentDatesBuilder {" +
				"paymentDatesReference=" + this.paymentDatesReference +
			'}';
		}
	}
}
